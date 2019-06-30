package org.group18.back.Service.Impl;

import org.group18.back.Dao.*;
import org.group18.back.Entity.*;
import org.group18.back.Model.GoodsSpecificationModel;
import org.group18.back.Model.HistroyGoodsModel;
import org.group18.back.Model.MyInfoPageModel;
import org.group18.back.Model.OrderPageModel;
import org.group18.back.Service.MyInfoService;
import org.group18.back.Utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import java.math.BigDecimal;
import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MyInfoServiceImpl implements MyInfoService {

    @Autowired
    UserAddressMapper useraddressMapper;
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    ShopMapper shopMapper;
    @Autowired
    OrderGoodsSpecificationYMapper orderGoodsSpecificationYMapper;
    @Autowired
    OrderGoodsSpecificationNMapper orderGoodsSpecificationNMapper;
    @Autowired
    GoodsSpecificationMapper goodsSpecificationMapper;
    @Autowired
    UserHistoryMapper userHistoryMapper;
    @Autowired
    GoodsReviewMapper goodsReviewMapper;

    @Override
    public boolean isSameDate(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);

        boolean isSameYear = cal1.get(Calendar.YEAR) == cal2
                .get(Calendar.YEAR);
        boolean isSameMonth = isSameYear
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH);
        boolean isSameDate = isSameMonth
                && cal1.get(Calendar.DAY_OF_MONTH) == cal2
                .get(Calendar.DAY_OF_MONTH);

        return isSameDate;
    }

    @Override
    public  ArrayList<HistroyGoodsModel> getHistoryGoods(String uid){
        ArrayList<HistroyGoodsModel> result = new ArrayList<>();
        UserHistoryExample userHistoryExample = new UserHistoryExample();
        userHistoryExample.setOrderByClause("create_date desc");//降序排列
        //查询最近30天的用户历史信息
        Date test =  new Date(System.currentTimeMillis() - 30*24*60*60*1000);
        userHistoryExample.or().andCreateDateGreaterThan(test).andUserUidEqualTo(uid);
        List<UserHistory> userHistoryList =  userHistoryMapper.selectByExample(userHistoryExample);
        //去除重复值（key为goodsUid）
        HashMap<Integer,UserHistory> cleanUserHistoy = new HashMap<>();
        for(UserHistory u : userHistoryList){
            if(!cleanUserHistoy.containsKey(u.getGoodsUid())) cleanUserHistoy.put(u.getGoodsUid(), u);
        }
        //按天进行分类
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String currentDate = sdf.format(new Date(System.currentTimeMillis()+1*24*60*60*1000));//初始化日期指针，提前一天，便于后面逻辑进行
        for(Map.Entry<Integer,UserHistory> entry: cleanUserHistoy.entrySet()){
            GoodsExample goodsExample = new GoodsExample();
            goodsExample.or().andUidEqualTo(entry.getValue().getGoodsUid());
            if(currentDate.equals(sdf.format(entry.getValue().getCreateDate()))){
                result.get(result.size()-1).getGoodsList().add(goodsMapper.selectByExample(goodsExample).get(0));
            }
            else {
                currentDate = sdf.format(entry.getValue().getCreateDate());//修改日期指针值
                HistroyGoodsModel histroyGoodsModel = new HistroyGoodsModel();
                histroyGoodsModel.setDate(entry.getValue().getCreateDate());
                List<Goods> goodsList = new ArrayList<>();
                goodsList.add(goodsMapper.selectByExample(goodsExample).get(0));
                histroyGoodsModel.setGoodsList(goodsList);
                result.add(histroyGoodsModel);
            }
        }
        return result;
    }

    @Override
    public List<UserAddress> myaddress(String uid){
        UserAddressExample useraddressExample1 = new UserAddressExample();
        UserAddressExample.Criteria criteria1 = useraddressExample1.createCriteria();
        criteria1.andUserUidEqualTo(uid);
        List<UserAddress> result = useraddressMapper.selectByExample(useraddressExample1);
        return result;
    }
    @Override
    @Transactional
    public Boolean edit(UserAddress userAddress) {
        Boolean result = true;
        UserAddressExample useraddressExample1 = new UserAddressExample();
        UserAddressExample.Criteria criteria1 = useraddressExample1.createCriteria();
        criteria1.andIdEqualTo(userAddress.getId());
        useraddressMapper.updateByExampleSelective(userAddress,useraddressExample1);
        result = true;
        return result;
    }

    @Override
    public List<OrderPageModel> getOrderPageInfo(String userUid,int pageSize, int page) {
        List<OrderPageModel> orderPageModelList = new ArrayList<>();
        //查询Order订单
        OrderExample orderExample = new OrderExample();
        orderExample.createCriteria().andUserUidEqualTo(userUid).andUserDeleteStateEqualTo(false);
        orderExample.setStartRow((page-1)*pageSize);
        orderExample.setPageSize(pageSize);
        List<Order> orderList = orderMapper.selectByExample(orderExample);
        for(Order order:orderList){
            OrderPageModel orderPageModel = new OrderPageModel();
            //设置订单信息
            orderPageModel.setOrderInfo(order);
            //查询店铺信息
            ShopExample shopExample = new ShopExample();
            shopExample.createCriteria().andSellerUidEqualTo(order.getSellerUid());
            orderPageModel.setShopBaseInfo(shopMapper.selectByExample(shopExample).get(0));
            //查询商品信息
            List<GoodsSpecificationModel> goodsSpecificationModelsY = getGoodsSpecificationY(order);
            List<GoodsSpecificationModel> goodsSpecificationModelsN = getGoodsSpecificationN(order);
            orderPageModel.setGSY(goodsSpecificationModelsY);
            orderPageModel.setGSN(goodsSpecificationModelsN);

            //设置总共商品数量、总价、总积分
            int totalAmount = 0, totalPoints = 0;
            BigDecimal totalPrice = new BigDecimal(0);
            for(GoodsSpecificationModel goodsSpecificationModel : goodsSpecificationModelsY){
                totalAmount+=goodsSpecificationModel.getAmount();
                totalPoints+=goodsSpecificationModel.getGoods().getPoints();
            }
            for(GoodsSpecificationModel goodsSpecificationModel : goodsSpecificationModelsN){
                totalAmount+=goodsSpecificationModel.getAmount();
                totalPrice = totalPrice.add(goodsSpecificationModel.getGoods().getDiscountPrice());
            }
            orderPageModel.setTotalAmount(totalAmount);
            orderPageModel.setTotalPoints(totalPoints);
            orderPageModel.setTotalPrice(totalPrice);
            orderPageModelList.add(orderPageModel);
        }
        return orderPageModelList;
    }

    @Override
    public Long getUserOrderCount(String userUid) {
        OrderExample orderExample = new OrderExample();
        orderExample.createCriteria().andUserUidEqualTo(userUid);
        long count = orderMapper.countByExample(orderExample);
        return count;
    }

    @Override
    @Transactional
    public void deleteUserOrder(Integer orderId) {
        OrderExample orderExample = new OrderExample();
        orderExample.createCriteria().andIdEqualTo(orderId);
        Order order = orderMapper.selectByExample(orderExample).get(0);
        order.setUserDeleteState(true);
        orderMapper.updateByExampleSelective(order, orderExample);
    }

    @Override
    @Transactional
    public void reviewGoods(Integer goodsUid, Integer specificationUid, String userUid, String payWay, String review) {
        GoodsReview goodsReview = new GoodsReview();
        goodsReview.setCreateDate(new Date(System.currentTimeMillis()));
        goodsReview.setGoodsLevel(5);
        goodsReview.setGoodsSpecificationUid(specificationUid);
        goodsReview.setGoodsUid(goodsUid);
        goodsReview.setReview(review);
        goodsReview.setUserUid(userUid);
        goodsReviewMapper.insert(goodsReview);
        if(payWay.equals("money")){
            OrderGoodsSpecificationNExample orderGoodsSpecificationNExample = new OrderGoodsSpecificationNExample();
            orderGoodsSpecificationNExample.createCriteria().andGoodsUidEqualTo(goodsUid).andSpecificationUidEqualTo(specificationUid);
            OrderGoodsSpecificationN orderGoodsSpecificationN = orderGoodsSpecificationNMapper.selectByExample(orderGoodsSpecificationNExample).get(0);
            orderGoodsSpecificationN.setReviewState(true);
            orderGoodsSpecificationNMapper.updateByExampleSelective(orderGoodsSpecificationN, orderGoodsSpecificationNExample);
        }
        else {
            OrderGoodsSpecificationYExample orderGoodsSpecificationYExample = new OrderGoodsSpecificationYExample();
            orderGoodsSpecificationYExample.createCriteria().andGoodsUidEqualTo(goodsUid).andSpecificationUidEqualTo(specificationUid);
            OrderGoodsSpecificationY orderGoodsSpecificationY = orderGoodsSpecificationYMapper.selectByExample(orderGoodsSpecificationYExample).get(0);
            orderGoodsSpecificationY.setReviewState(true);
            orderGoodsSpecificationYMapper.updateByExampleSelective(orderGoodsSpecificationY, orderGoodsSpecificationYExample);
        }

    }

    @Override
    public List<UserAddress> getUserAddressList(String userUid) {
        UserAddressExample addressExample = new UserAddressExample();
        addressExample.createCriteria().andUserUidEqualTo(userUid);
        return useraddressMapper.selectByExample(addressExample);
    }

    @Override
    public void deleteUserAddress(Integer addressId) {
        UserAddressExample userAddressExample = new UserAddressExample();
        userAddressExample.createCriteria().andIdEqualTo(addressId);
        useraddressMapper.deleteByExample(userAddressExample);
    }

    @Override
    public void editUserAddress(UserAddress userAddress) {
        useraddressMapper.updateByPrimaryKeySelective(userAddress);
    }


    private List<GoodsSpecificationModel> getGoodsSpecificationY(Order order){
        List<GoodsSpecificationModel> goodsSpecificationModels = new ArrayList<>();
        OrderGoodsSpecificationYExample orderGoodsSpecificationYExample = new OrderGoodsSpecificationYExample();
        orderGoodsSpecificationYExample.createCriteria().andOrderUidEqualTo(order.getId());
        List<OrderGoodsSpecificationY> orderGoodsSpecificationYList = orderGoodsSpecificationYMapper.selectByExample(orderGoodsSpecificationYExample);
        if(orderGoodsSpecificationYList == null) return null;
        for(OrderGoodsSpecificationY o:orderGoodsSpecificationYList){
            GoodsSpecificationModel goodsSpecificationModel = new GoodsSpecificationModel();
            goodsSpecificationModel.setReviewState(o.getReviewState());//设置评论状态
            goodsSpecificationModel.setAmount(o.getAmount());//设置购买数量
            GoodsExample goodsExample = new GoodsExample();
            goodsExample.createCriteria().andUidEqualTo(o.getGoodsUid());
            goodsSpecificationModel.setGoods(goodsMapper.selectByExample(goodsExample).get(0));
            GoodsSpecificationExample goodsSpecificationExample = new GoodsSpecificationExample();
            goodsSpecificationExample.createCriteria().andUidEqualTo(o.getSpecificationUid());
            goodsSpecificationModel.setGoodsSpecification(goodsSpecificationMapper.selectByExample(goodsSpecificationExample).get(0));
            goodsSpecificationModels.add(goodsSpecificationModel);
        }
        return goodsSpecificationModels;
    }

    private List<GoodsSpecificationModel> getGoodsSpecificationN(Order order){
        List<GoodsSpecificationModel> goodsSpecificationModels = new ArrayList<>();
        OrderGoodsSpecificationNExample orderGoodsSpecificationNExample = new OrderGoodsSpecificationNExample();
        orderGoodsSpecificationNExample.createCriteria().andOrderUidEqualTo(order.getId());
        List<OrderGoodsSpecificationN> orderGoodsSpecificationNList = orderGoodsSpecificationNMapper.selectByExample(orderGoodsSpecificationNExample);
        if(orderGoodsSpecificationNList == null) return null;
        for(OrderGoodsSpecificationN o:orderGoodsSpecificationNList){
            GoodsSpecificationModel goodsSpecificationModel = new GoodsSpecificationModel();
            goodsSpecificationModel.setReviewState(o.getReviewState());//设置评论状态
            goodsSpecificationModel.setAmount(o.getAmount());//设置购买数量
            GoodsExample goodsExample = new GoodsExample();
            goodsExample.createCriteria().andUidEqualTo(o.getGoodsUid());
            goodsSpecificationModel.setGoods(goodsMapper.selectByExample(goodsExample).get(0));
            GoodsSpecificationExample goodsSpecificationExample = new GoodsSpecificationExample();
            goodsSpecificationExample.createCriteria().andUidEqualTo(o.getSpecificationUid());
            goodsSpecificationModel.setGoodsSpecification(goodsSpecificationMapper.selectByExample(goodsSpecificationExample).get(0));
            goodsSpecificationModels.add(goodsSpecificationModel);
        }
        return goodsSpecificationModels;
    }
}
