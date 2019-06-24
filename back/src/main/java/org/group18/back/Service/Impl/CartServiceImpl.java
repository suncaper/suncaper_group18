package org.group18.back.Service.Impl;

import org.group18.back.Dao.CartMapper;
import org.group18.back.Entity.Cart;
import org.group18.back.Entity.CartExample;
import org.group18.back.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService{
    @Autowired
    private CartMapper cartMapper;
    @Override
    public Cart getCart(String user_uid, int specifiation_uid){
        CartExample cartExample = new CartExample();
        CartExample.Criteria criteria = cartExample.createCriteria();
        criteria.andUserUidEqualTo(user_uid);
        criteria.andSpecificationUidEqualTo(specifiation_uid);
        List<Cart> cartList = cartMapper.selectByExample(cartExample);
        Cart cart = cartList.get(0);
        return cart;
    }

    @Override
    public void addAndUpdateCart(Cart cart, String user_uid, int goods_uid, int specification_uid, int counts){
        if (cart == null){    //购物车中没有此规格的商品
            Cart cart1 = new Cart();
            cart1.setUserUid(user_uid);
            cart1.setGoodsUid(goods_uid);
            cart1.setSpecificationUid(specification_uid);
            cart1.setDate(new Date(System.currentTimeMillis()));
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
    public void increaseCart(Cart cart, String user_uid, int specification_uid) {
        cart.setAmount(cart.getAmount() + 1);
        Cart cart1 = new Cart();
        cart1.setId(cart.getId());
        cart1.setAmount(cart.getAmount());
        cartMapper.updateByPrimaryKey(cart1);
    }

    @Override
    public Map<String, String> decreaseCart(Cart cart, String user_uid, int specification_uid) {
        Map<String,String> result = new HashMap<>();
        if (cart.getAmount() - 1 < 1){
            result.put("msg", "已不能继续减少");
            return result;
        }
        cart.setAmount(cart.getAmount() - 1);
        Cart cart1 = new Cart();
        cart1.setId(cart.getId());
        cart1.setAmount(cart.getAmount());
        cartMapper.updateByPrimaryKey(cart1);
        result.put("msg", "已减少一件");
        return result;
    }

    @Override
    public boolean deleteCart(String user_uid, int specification_uid){
        CartExample cartExample = new CartExample();
        CartExample.Criteria criteria = cartExample.createCriteria();
        criteria.andUserUidEqualTo(user_uid);
        criteria.andSpecificationUidEqualTo(specification_uid);
        cartMapper.deleteByExample(cartExample);
        return true;
    }

    @Override
    public List<Cart> getCarts(String user_uid) {
        CartExample cartExample = new CartExample();
        CartExample.Criteria criteria = cartExample.createCriteria();
        criteria.andUserUidEqualTo(user_uid);
        List<Cart> cartList = cartMapper.selectByExample(cartExample);
        return cartList;
    }

}
