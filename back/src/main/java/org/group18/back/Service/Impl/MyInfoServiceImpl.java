package org.group18.back.Service.Impl;

import org.group18.back.Dao.*;
import org.group18.back.Entity.*;
import org.group18.back.Model.GoodsSpecificationModel;
import org.group18.back.Model.OrderPageModel;
import org.group18.back.Service.MyInfoService;
import org.group18.back.Utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import java.math.BigDecimal;
import java.sql.Date;
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
