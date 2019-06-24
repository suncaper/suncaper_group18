package org.group18.back.Controller;

import org.group18.back.Entity.Cart;
import org.group18.back.Service.CartService;
import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class CartController {


    @Resource
    private CartService cartService;

    @RequestMapping(value = "/Cart")
    public String cart(){
        return "cart";
    }

    //商品加入购物车
    @RequestMapping(value = "/addCart", method = RequestMethod.POST)
    @ResponseBody
    public String addCart(String user_uid, int goods_uid, int specification_uid, int counts){
        Cart cart = cartService.getCart(user_uid, specification_uid);
        cartService.addAndUpdateCart(cart, user_uid, goods_uid, specification_uid, counts);
        return "deal_single";
    }

    //获取用户购物车数据
    @RequestMapping(value =  "/getCart", method = RequestMethod.POST)
    @ResponseBody
    public String getCart(String user_uid){
        List<Cart> cartList = cartService.getCarts(user_uid);
        JSONArray jsonArray = new JSONArray(cartList);
        String carts = jsonArray.toString();
        return "cart";
    }

    //删除商品
    @RequestMapping(value = "/deleteCarts", method = RequestMethod.POST)
    @ResponseBody
    public String deleteCarts(String user_uid, int specification_uid){
        cartService.deleteCart(user_uid, specification_uid);
        return "cart";
    }

    //增加商品数量
    @RequestMapping(value = "/increaseCarts", method = RequestMethod.POST)
    @ResponseBody
    public String incereaseCarts(String user_uid, int specification_uid){
        Cart cart = cartService.getCart(user_uid, specification_uid);
        cartService.increaseCart(cart, user_uid, specification_uid);
        return  "cart";
    }

    //减少商品数量
    @RequestMapping(value = "/decreaseCarts", method = RequestMethod.POST)
    @ResponseBody
    public String decereaseCarts(String user_uid, int specification_uid){
        Cart cart = cartService.getCart(user_uid, specification_uid);
        cartService.decreaseCart(cart, user_uid, specification_uid);
        return  "cart";
    }
}
