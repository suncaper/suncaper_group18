package org.group18.back.Service.Impl;

import org.group18.back.Dao.*;
import org.group18.back.Entity.*;
import org.group18.back.Model.CartListModel;
import org.group18.back.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    private CartMapper cartMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private GoodsSpecificationMapper goodsSpecificationMapper;

    @Override
    public Cart getCart(String user_uid, Integer specifiation_uid){
        CartExample cartExample = new CartExample();
        CartExample.Criteria criteria = cartExample.createCriteria();
        criteria.andUserUidEqualTo(user_uid);
        criteria.andSpecificationUidEqualTo(specifiation_uid);
        List<Cart> cartList = cartMapper.selectByExample(cartExample);
        Cart cart = cartList.get(0);
        return cart;
    }

    @Override
    public void addAndUpdateCart(Cart cart, String user_uid, Integer goods_uid, Integer specification_uid, Integer counts){
        if (cart == null){    //购物车中没有此规格的商品
            Cart cart1 = new Cart();
            cart1.setUserUid(user_uid);
            cart1.setGoodsUid(goods_uid);
            cart1.setSpecificationUid(specification_uid);
            cart1.setCreateDate(new Date(System.currentTimeMillis()));
            cart1.setAmount(counts);
            cartMapper.insert(cart);
        }else{
            cart.setAmount(cart.getAmount() + counts);
            Cart cart1 = new Cart();
            cart1.setId(cart.getId());
            cart1.setAmount(cart.getAmount());
            cartMapper.updateByPrimaryKey(cart1);
        }
    }

    @Override
    public void increaseCart(String user_uid, Integer specification_uid) {
        CartExample cartExample = new CartExample();
        CartExample.Criteria criteria = cartExample.createCriteria();
        criteria.andUserUidEqualTo(user_uid);
        criteria.andSpecificationUidEqualTo(specification_uid);
        List<Cart> cartList = cartMapper.selectByExample(cartExample);
        cartList.get(0).setId(cartList.get(0).getId());
        cartList.get(0).setAmount(cartList.get(0).getAmount() + 1);
        cartMapper.updateByPrimaryKey(cartList.get(0));
//        cart.setAmount(cart.getAmount() + 1);
//        Cart cart1 = new Cart();
//        cart1.setId(cart.getId());
//        cart1.setAmount(cart.getAmount());
//        cartMapper.updateByPrimaryKey(cart1);
    }

    @Override
    public void decreaseCart(String user_uid, Integer specification_uid) {
        CartExample cartExample = new CartExample();
        CartExample.Criteria criteria = cartExample.createCriteria();
        criteria.andUserUidEqualTo(user_uid);
        criteria.andSpecificationUidEqualTo(specification_uid);
        List<Cart> cartList = cartMapper.selectByExample(cartExample);
//        if (cartList.get(0).getAmount() - 1 < 1){
//            result.put("msg", "已不能继续减少");
//            return result;
//        }
        cartList.get(0).setId(cartList.get(0).getId());
        cartList.get(0).setAmount(cartList.get(0).getAmount() - 1);
        cartMapper.updateByPrimaryKey(cartList.get(0));
//        result.put("msg", "已减少一件");
//        return result;
    }

    @Override
    public boolean deleteCart(String user_uid, Integer specification_uid){
        CartExample cartExample = new CartExample();
        CartExample.Criteria criteria = cartExample.createCriteria();
        criteria.andUserUidEqualTo(user_uid);
        criteria.andSpecificationUidEqualTo(specification_uid);
        cartMapper.deleteByExample(cartExample);
        return true;
    }

    @Override
    public List<CartListModel> getCarts(String user_uid) {
        List<CartListModel> cartListModels = new ArrayList<>();
        CartExample cartExample = new CartExample();
        CartExample.Criteria criteria = cartExample.createCriteria();
        criteria.andUserUidEqualTo(user_uid);
        List<Cart> cartList = cartMapper.selectByExample(cartExample);
        for (Integer i = 0; i < cartList.size(); i++){
            CartListModel cartListModel = new CartListModel();
            cartListModel.setAmount(cartList.get(i).getAmount());//数量
//            System.out.println(cartList.get(i).getAmount());
            cartListModel.setUser_uid(cartList.get(i).getUserUid());//user_uid
            cartListModel.setSpecification_uid(cartList.get(i).getSpecificationUid());//specification_uid

            //查Goods表
            GoodsExample goodsExample = new GoodsExample();
            GoodsExample.Criteria criteria1 = goodsExample.createCriteria();
            criteria1.andUidEqualTo(cartList.get(i).getGoodsUid());
            List<Goods> goods = goodsMapper.selectByExample(goodsExample);
            cartListModel.setGoods_uid(goods.get(0).getUid());//商品uid
            cartListModel.setGoods_name(goods.get(0).getGoodsName());//商品名
//            System.out.println(goods.get(0).getGoodsName());
            cartListModel.setDiscount_price(goods.get(0).getDiscountPrice());//优惠价格
            cartListModel.setPrice(goods.get(0).getPrice());//价格
//            System.out.println(goods.get(0).getDiscountPrice());
            cartListModel.setImg_url(goods.get(0).getImgUrl());//图片地址
//            System.out.println(goods.get(0).getImgUrl());
            cartListModel.setIs_exchange(goods.get(0).getIsExchange());//是否使用积分
//            System.out.println(goods.get(0).getIsExchange());
            cartListModel.setPoints(goods.get(0).getPoints());//积分价格

            //查shop表
            ShopExample shopExample = new ShopExample();
            ShopExample.Criteria criteria3 = shopExample.createCriteria();
            criteria3.andSellerUidEqualTo(goods.get(0).getSellerUid());
            List<Shop> shops = shopMapper.selectByExample(shopExample);
            cartListModel.setShop_name(shops.get(0).getShopName());//商店名
//            System.out.println(shops.get(0).getShopName());

            //查goods_specification表
            GoodsSpecificationExample goodsSpecificationExample = new GoodsSpecificationExample();
            GoodsSpecificationExample.Criteria criteria2 = goodsSpecificationExample.createCriteria();
            criteria2.andUidEqualTo(cartList.get(0).getSpecificationUid());
            List<GoodsSpecification> goodsSpecifications = goodsSpecificationMapper.selectByExample(goodsSpecificationExample);
            cartListModel.setSpecification_name(goodsSpecifications.get(0).getSpecificationName());
//            System.out.println(goodsSpecifications.get(0).getSpecificationName());//规格名

            cartListModels.add(cartListModel);
        }
        return cartListModels;
    }

    @Override
    public BigDecimal getTotalPrice(List<CartListModel> cartList){
        BigDecimal totalPrice = new BigDecimal("0.0");
        for (Integer i = 0; i < cartList.size(); i++){
            if (!cartList.get(i).isIs_exchange())
                totalPrice = totalPrice.add(cartList.get(i).getDiscount_price().multiply(new BigDecimal(cartList.get(i).getAmount())));
        }
        System.out.println(totalPrice);
        return totalPrice;
    }

    @Override
    public Integer getTotalPoints(List<CartListModel> cartList){
        Integer totalPoints = 0;
        for (Integer i = 0; i < cartList.size(); i++){
            if (cartList.get(i).isIs_exchange())
                totalPoints = totalPoints + cartList.get(i).getPoints() * cartList.get(i).getAmount();
        }
        return totalPoints;
    }

    @Override
    public Map<String, List<CartListModel>> getShopCarts (List<CartListModel> cartListModels){
        Map<String, List<CartListModel>> shopCartsMap = new HashMap<String, List<CartListModel>>();
        List<Map<String, List<CartListModel>>> shopCartsList = new ArrayList<>();
        for(Integer i = 0; i < cartListModels.size(); i++){
            String shopName = cartListModels.get(i).getShop_name();
            List<CartListModel> cartListModels1 = new ArrayList<>();
            System.out.println(shopName);
            if(shopCartsMap.containsKey(cartListModels.get(i).getShop_name()) == false){
                for(Integer j = 0; j < cartListModels.size(); j++){
                    if (cartListModels.get(j).getShop_name().equals(shopName)){
                        cartListModels1.add(cartListModels.get(j));
                    }
                }
                shopCartsMap.put(shopName, cartListModels1);
            }
        }
//        shopCartsList.add(shopCartsMap);
//        System.out.println(shopCartsMap);
        return shopCartsMap;
    }
    public Integer getTotalAmount(List<CartListModel> cartList){
        Integer totalAmount = 0;
        for (Integer i = 0; i < cartList.size(); i++){
            totalAmount = totalAmount + cartList.get(i).getAmount();
        }
        return totalAmount;
    }
}
