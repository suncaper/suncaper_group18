package org.group18.back.Service.Impl;

import org.group18.back.Dao.*;
import org.group18.back.Entity.*;
import org.group18.back.Model.CartListModel;
import org.group18.back.Service.CartService;
import org.group18.back.Service.CheckoutService;
import org.group18.back.Utils.SNUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CheckoutServiceImpl implements CheckoutService {
    @Autowired
    private UserAddressMapper userAddressMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GoodsSpecificationMapper goodsSpecificationMapper;
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private CartService cartService;
    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderGoodsSpecificationNMapper orderGoodsSpecificationNMapper;
    @Autowired
    private OrderGoodsSpecificationYMapper orderGoodsSpecificationYMapper;

    @Override
    public List<UserAddress> getUserAddress(String user_uid) {
        UserAddressExample userAddressExample = new UserAddressExample();
        UserAddressExample.Criteria criteria = userAddressExample.createCriteria();
        criteria.andUserUidEqualTo(user_uid);
        List<UserAddress> userAddressList = userAddressMapper.selectByExample(userAddressExample);
        return userAddressList;
    }

    @Override
    public BigDecimal getUserBalance(String user_uid) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUidEqualTo(user_uid);
        List<User> user = userMapper.selectByExample(userExample);
        return user.get(0).getBalance();
    }

    @Override
    public Integer getUserPoints(String user_uid) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUidEqualTo(user_uid);
        List<User> user = userMapper.selectByExample(userExample);
        return user.get(0).getPoints();
    }

    @Override
    public Integer payment(String user_uid, BigDecimal totalPrice, Integer totalPoints, BigDecimal balance, Integer points, String checkout_method, Map<String, List<CartListModel>> shopCartList, List<CartListModel> cartList) {
        Integer Abnormal_specification_uid = 0;//异常商品的specification_uid

        for (Integer i = 0; i < cartList.size(); i++) {//判断库存
            GoodsSpecificationExample goodsSpecificationExample = new GoodsSpecificationExample();
            GoodsSpecificationExample.Criteria criteria = goodsSpecificationExample.createCriteria();
            criteria.andUidEqualTo(cartList.get(i).getSpecification_uid());
            List<GoodsSpecification> goodsSpecifications = goodsSpecificationMapper.selectByExample(goodsSpecificationExample);
            if (goodsSpecifications.get(0).getAmount() > cartList.get(i).getAmount())
                continue;
            else {
                Abnormal_specification_uid = cartList.get(i).getSpecification_uid();
                return 2;//库存不足
            }
        }

        //生成订单
        //删除cart
        for (Integer i = 0; i < cartList.size(); i++){
            CartExample cartExample = new CartExample();
            CartExample.Criteria criteria = cartExample.createCriteria();
            criteria.andUserUidEqualTo(user_uid);
            criteria.andSpecificationUidEqualTo(cartList.get(i).getSpecification_uid());
            cartMapper.deleteByExample(cartExample);
        }
        System.out.println("cartSuccess");
        //更新goods库存
        for (Integer i = 0; i < cartList.size(); i++){
            GoodsExample goodsExample = new GoodsExample();
            GoodsExample.Criteria criteria = goodsExample.createCriteria();
            criteria.andUidEqualTo(cartList.get(i).getGoods_uid());
            List<Goods> goods = goodsMapper.selectByExample(goodsExample);
            goods.get(0).setUid(goods.get(0).getUid());
            goods.get(0).setAmount(goods.get(0).getAmount()-cartList.get(i).getAmount());
            goodsMapper.updateByPrimaryKey(goods.get(0));
        }

        //更新specification库存
        for (Integer i = 0; i < cartList.size(); i++){
            GoodsSpecificationExample goodsSpecificationExample = new GoodsSpecificationExample();
            GoodsSpecificationExample.Criteria criteria = goodsSpecificationExample.createCriteria();
            criteria.andUidEqualTo(cartList.get(i).getSpecification_uid());
            List<GoodsSpecification> goodsSpecifications = goodsSpecificationMapper.selectByExample(goodsSpecificationExample);
            goodsSpecifications.get(0).setUid(goodsSpecifications.get(0).getUid());
            goodsSpecifications.get(0).setAmount(goodsSpecifications.get(0).getAmount()-cartList.get(i).getAmount());//update Amount
            goodsSpecifications.get(0).setSaleAmount(goodsSpecifications.get(0).getSaleAmount()+cartList.get(i).getAmount());//update SaleAmount
            goodsSpecificationMapper.updateByPrimaryKey(goodsSpecifications.get(0));
        }

        if (totalPoints <= points) {//优先判断积分
            switch (checkout_method) {
                case "1":
                    if (totalPrice.compareTo(balance) <= 0) {
                        UserExample userExample = new UserExample();
                        UserExample.Criteria criteria = userExample.createCriteria();
                        criteria.andUidEqualTo(user_uid);
                        List<User> user = userMapper.selectByExample(userExample);
                        user.get(0).setUid(user.get(0).getUid());
                        user.get(0).setBalance(balance.subtract(totalPrice));//更新余额
                        int addpoint = totalPrice.divide(new BigDecimal(0.01), 0, BigDecimal.ROUND_HALF_UP).intValue();
                        user.get(0).setPoints(points - totalPoints + addpoint);//更新积分
                        userMapper.updateByPrimaryKey(user.get(0));
                        return 1;//成功
                    } else
                        return 3;//余额不足
                case "2":
                    return 1;//成功
                case "3":
                    return 1;//成功
            }
        } else {
            return 4;//积分不足
        }
        return 0;
    }

    @Override
    public void generateOrder(String user_uid, String checkout_method, Map<String, List<CartListModel>> shopCartList, Integer addressId, Integer result){
        for (String key: shopCartList.keySet()){
            Order order = new Order();
            String Sn = SNUtils.getSnUtils(user_uid);
            order.setSn(Sn);
            BigDecimal orderDiscountPrice = cartService.getTotalPrice(shopCartList.get(key));
            order.setDiscountPrice(orderDiscountPrice);
            BigDecimal orderPrice = new BigDecimal("0.0");
            boolean isExchange = false;
            for (Integer i = 0; i < shopCartList.get(key).size(); i++){
                orderPrice = orderPrice.add(shopCartList.get(key).get(i).getDiscount_price().multiply(new BigDecimal(shopCartList.get(key).get(i).getAmount())));
                if (shopCartList.get(key).get(i).isIs_exchange())
                    isExchange = true;
            }
            order.setPrice(orderPrice);//orderPrice
            order.setCreateDate(new Date(System.currentTimeMillis()));//Date
            order.setStateUid("state01");//StateUid
            order.setTypeUid("type01");//TypeUid
            order.setUserUid(user_uid);//UserUid
            ShopExample shopExample = new ShopExample();
            ShopExample.Criteria criteria = shopExample.createCriteria();
            criteria.andShopNameEqualTo(key);
            List<Shop> shops = shopMapper.selectByExample(shopExample);
            order.setSellerUid(shops.get(0).getSellerUid());//SellerUid
            switch (result){//is_paid
                case 1:
                    order.setIsPaid(true);
                    break;
                default:
                    order.setIsPaid(false);
            }
            switch (checkout_method){//method_id,paid_by_points,paid_by_money
                case "1":
                    if (isExchange && !orderDiscountPrice.equals(0)){
                        order.setMethodUid("method06");
                        order.setPaidByPoints(true);
                        order.setPaidByMoney(true);
                    }
                    else if (isExchange && orderDiscountPrice.equals(0)){
                        order.setMethodUid("method07");
                        order.setPaidByPoints(true);
                        order.setPaidByMoney(false);
                    }
                    else{
                        order.setMethodUid("method03");
                        order.setPaidByPoints(false);
                        order.setPaidByMoney(true);
                    }
                    break;
                case "2":
                    if (isExchange && !orderDiscountPrice.equals(0.00)){
                        order.setMethodUid("method04");
                        order.setPaidByPoints(true);
                        order.setPaidByMoney(true);
                    }
                    else if (isExchange && orderDiscountPrice.equals(0.00)){
                        order.setMethodUid("method07");
                        order.setPaidByPoints(true);
                        order.setPaidByMoney(false);
                    }
                    else{
                        order.setMethodUid("method01");
                        order.setPaidByPoints(false);
                        order.setPaidByMoney(true);
                    }
                    break;
                case "3":
                    if (isExchange && !orderDiscountPrice.equals(0)){
                        order.setMethodUid("method05");
                        order.setPaidByPoints(true);
                        order.setPaidByMoney(true);
                    }
                    else if (isExchange && orderDiscountPrice.equals(0)){
                        order.setMethodUid("method07");
                        order.setPaidByPoints(true);
                        order.setPaidByMoney(false);
                    }
                    else{
                        order.setMethodUid("method02");
                        order.setPaidByPoints(false);
                        order.setPaidByMoney(true);
                    }
                    break;
            }
            order.setAddressId(addressId);
            order.setUserDeleteState(false);
            order.setSellerDeleteState(false);
            orderMapper.insert(order);//插入order表

            OrderExample orderExample = new OrderExample();
            OrderExample.Criteria criteria1 = orderExample.createCriteria();
            criteria1.andSnEqualTo(Sn);
            List<Order> orders = orderMapper.selectByExample(orderExample);
            Integer order_uid = orders.get(0).getId();
            for (Integer i = 0; i < shopCartList.get(key).size(); i++){
                if (shopCartList.get(key).get(i).isIs_exchange()){//插入t_order_goods_specification_y
                    OrderGoodsSpecificationY orderGoodsSpecificationY = new OrderGoodsSpecificationY();
                    orderGoodsSpecificationY.setOrderUid(order_uid);
                    orderGoodsSpecificationY.setGoodsUid(shopCartList.get(key).get(i).getGoods_uid());
                    orderGoodsSpecificationY.setSpecificationUid(shopCartList.get(key).get(i).getSpecification_uid());
                    orderGoodsSpecificationY.setAmount(shopCartList.get(key).get(i).getAmount());
                    orderGoodsSpecificationY.setPoints(shopCartList.get(key).get(i).getPoints());
                    orderGoodsSpecificationY.setReviewState(false);
                    orderGoodsSpecificationYMapper.insert(orderGoodsSpecificationY);
                }else{//插入t_order_goods_specification_n
                    OrderGoodsSpecificationN orderGoodsSpecificationN = new OrderGoodsSpecificationN();
                    orderGoodsSpecificationN.setOrderUid(order_uid);
                    orderGoodsSpecificationN.setGoodsUid(shopCartList.get(key).get(i).getGoods_uid());
                    orderGoodsSpecificationN.setSpecificationUid(shopCartList.get(key).get(i).getSpecification_uid());
                    orderGoodsSpecificationN.setAmount(shopCartList.get(key).get(i).getAmount());
                    System.out.println(shopCartList.get(key).get(i).getPrice());
                    orderGoodsSpecificationN.setPrice(shopCartList.get(key).get(i).getPrice());
                    orderGoodsSpecificationN.setDiscountPrice(shopCartList.get(key).get(i).getDiscount_price());
                    orderGoodsSpecificationN.setReviewState(false);
                    orderGoodsSpecificationNMapper.insert(orderGoodsSpecificationN);
                }
            }
        }
        System.out.println("Yes");
    }
}
