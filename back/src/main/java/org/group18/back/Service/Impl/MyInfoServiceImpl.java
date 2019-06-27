package org.group18.back.Service.Impl;

import org.group18.back.Dao.*;
import org.group18.back.Entity.*;
import org.group18.back.Model.GoodsSpecificationModel;
import org.group18.back.Model.HistroyGoodsModel;
import org.group18.back.Model.OrderPageModel;
import org.group18.back.Service.MyInfoService;
import org.group18.back.Utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import java.math.BigDecimal;
import java.sql.Array;
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
    public  ArrayList<HistroyGoodsModel> getHistoryGoods(String uid) {
        UserHistoryExample userHistoryExample = new UserHistoryExample();
        UserHistoryExample.Criteria criteria1 = userHistoryExample.createCriteria();
        criteria1.andUserUidEqualTo(uid);
        List<UserHistory> userHistory1 = userHistoryMapper.selectByExample(userHistoryExample);//从userhistory表中取出uid为当前用户的记录
        ArrayList<UserHistory> userHistory2 = new ArrayList<UserHistory>();
        for(int i=0;i<userHistory1.size();i++) //去掉uid重复的记录,并保留最新的createDate值
        {
            if(userHistory2.isEmpty())
            {
                userHistory2.add(userHistory1.get(i));
            }
            else
            {
                boolean addinto=true;
                for(int j=0;j<userHistory2.size();j++)
                {
                    if(userHistory2.get(j).getGoodsUid()==userHistory1.get(i).getGoodsUid())
                    {
                        addinto=false;
                        if(userHistory2.get(j).getCreateDate().before(userHistory1.get(i).getCreateDate()))
                            userHistory2.get(j).setCreateDate(userHistory1.get(i).getCreateDate());//更新createDate
                        break;
                    }
                }
                if(addinto==true)
                {
                    userHistory2.add(userHistory1.get(i));
                }
            }
        }
        ArrayList<Goods> goodsinfo1 = new ArrayList<Goods>();
        for(int i=0;i<userHistory2.size();i++)
        {
            GoodsExample goodsExample = new GoodsExample();
            GoodsExample.Criteria criteria2 = goodsExample.createCriteria();
            criteria2.andUidEqualTo(userHistory2.get(i).getGoodsUid());
            goodsinfo1.addAll(goodsMapper.selectByExample(goodsExample));
        }

        ArrayList<HistroyGoodsModel> histroyGoodsModelList = new ArrayList<HistroyGoodsModel>();
        int j=0;
        int size = userHistory2.size();
        Date date1 = new Date();
        Date date2 = new Date();
        ArrayList<Goods> goodsinfo2 = new ArrayList<Goods>();
        goodsinfo2.add(goodsinfo1.get(size-1));
        HistroyGoodsModel temp = new HistroyGoodsModel();
        histroyGoodsModelList.add(0,temp);
        histroyGoodsModelList.get(0).setDate(userHistory2.get(size-1).getCreateDate());
        for(int i=size-1;i>0;i--)
        {
            date1 = userHistory2.get(i).getCreateDate();
            date2 = userHistory2.get(i-1).getCreateDate();
            if(isSameDate(date1,date2)==true)
            {
                goodsinfo2.add(goodsinfo1.get(i-1));
            }
            else
            {
                histroyGoodsModelList.get(j).setGoodsList(goodsinfo2);
                j++;
                histroyGoodsModelList.get(j).setDate(date2);
                goodsinfo2.clear();
                goodsinfo2.add(goodsinfo1.get(i-1));
            }
        }

        return histroyGoodsModelList;
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
        orderExample.createCriteria().andUserUidEqualTo(userUid);
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
