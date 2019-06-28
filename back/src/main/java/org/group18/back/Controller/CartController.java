package org.group18.back.Controller;

import org.group18.back.Entity.Cart;
import org.group18.back.Entity.User;
import org.group18.back.Model.CartListModel;
import org.group18.back.Service.CartService;
import org.group18.back.Service.LoginRegisterService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CartController {

    @Autowired
    LoginRegisterService loginRegisterService;

    @Resource
    private CartService cartService;

    //获取用户购物车数据
    @RequestMapping("/Cart")
    public String cart(Model model, HttpServletRequest request){
        //检查是否已经登陆
        User user = loginRegisterService.checkLoginStatus(request.getCookies());
        if(user == null) {
            model.addAttribute("user", new User());
            model.addAttribute("isSignin", false);
            return "signin";
        }
        else {
            model.addAttribute("isSignin", true);
            model.addAttribute("user", user);
            List<CartListModel> cartList = cartService.getCarts(user.getUid());
            BigDecimal totalPrice = cartService.getTotalPrice(cartList);
            model.addAttribute("totalPrice", totalPrice);
            Integer totalAmount = cartService.getTotalAmount(cartList);
            model.addAttribute("totalAmount", totalAmount);
            Integer totalPoints = cartService.getTotalPoints(cartList);
            model.addAttribute("totalPoints", totalPoints);
            Map<String, List<CartListModel>> shopCartList = cartService.getShopCarts(cartList);
            model.addAttribute("shopCartList", shopCartList);
            return "cart";
        }
    }

//    //商品加入购物车
//    @RequestMapping("/addCart")
//    public String addCart(Model model, HttpServletRequest request, int specification_uid, int goods_uid, int counts){
//        //检查是否已经登陆
//        User user = loginRegisterService.checkLoginStatus(request.getCookies());
//        if(user == null) {
//            model.addAttribute("user", new User());
//            model.addAttribute("isSignin", false);
//            return "signin";
//        }
//        else {
//            model.addAttribute("isSignin", true);
//            model.addAttribute("user", user);
//            Cart cart = cartService.getCart(user.getUid(), specification_uid);
//            cartService.addAndUpdateCart(cart, user.getUid(), goods_uid, specification_uid, counts);
//            return "deal_single";
//        }
//    }

    //删除商品
    @RequestMapping("/deleteCarts")
    public String deleteCarts(Model model, HttpServletRequest request, @RequestParam("specification_uid") Integer specification_uid){
        //检查是否已经登陆
        User user = loginRegisterService.checkLoginStatus(request.getCookies());
        if(user == null) {
            model.addAttribute("user", new User());
            model.addAttribute("isSignin", false);
            return "signin";
        }
        else {
            model.addAttribute("isSignin", true);
            model.addAttribute("user", user);
            cartService.deleteCart(user.getUid(), specification_uid);
            List<CartListModel> cartList = cartService.getCarts(user.getUid());
            BigDecimal totalPrice = cartService.getTotalPrice(cartList);
            model.addAttribute("totalPrice", totalPrice);
            Integer totalAmount = cartService.getTotalAmount(cartList);
            model.addAttribute("totalAmount", totalAmount);
            Integer totalPoints = cartService.getTotalPoints(cartList);
            model.addAttribute("totalPoints", totalPoints);
            Map<String, List<CartListModel>> shopCartList = cartService.getShopCarts(cartList);
            model.addAttribute("shopCartList", shopCartList);
            return  "cart";
        }
    }

    //增加商品数量
    @RequestMapping("/increaseCarts")
    public String incereaseCarts(Model model, HttpServletRequest request, @RequestParam("specification_uid") Integer specification_uid){
        //检查是否已经登陆
        User user = loginRegisterService.checkLoginStatus(request.getCookies());
        if(user == null) {
            model.addAttribute("user", new User());
            model.addAttribute("isSignin", false);
            return "signin";
        }
        else {
            model.addAttribute("isSignin", true);
            model.addAttribute("user", user);
            cartService.increaseCart(user.getUid(), specification_uid);
            List<CartListModel> cartList = cartService.getCarts(user.getUid());
            BigDecimal totalPrice = cartService.getTotalPrice(cartList);
            model.addAttribute("totalPrice", totalPrice);
            Integer totalAmount = cartService.getTotalAmount(cartList);
            model.addAttribute("totalAmount", totalAmount);
            Integer totalPoints = cartService.getTotalPoints(cartList);
            model.addAttribute("totalPoints", totalPoints);
            Map<String, List<CartListModel>> shopCartList = cartService.getShopCarts(cartList);
            model.addAttribute("shopCartList", shopCartList);
            return  "cart";
        }
    }

    //减少商品数量
    @RequestMapping("/decreaseCarts")
    public String decereaseCarts(Model model, HttpServletRequest request, @RequestParam("specification_uid") Integer specification_uid){
        //检查是否已经登陆
        User user = loginRegisterService.checkLoginStatus(request.getCookies());
        if(user == null) {
            model.addAttribute("user", new User());
            model.addAttribute("isSignin", false);
            return "signin";
        }
        else {
            model.addAttribute("isSignin", true);
            model.addAttribute("user", user);
            cartService.decreaseCart(user.getUid(), specification_uid);
            List<CartListModel> cartList = cartService.getCarts(user.getUid());
            BigDecimal totalPrice = cartService.getTotalPrice(cartList);
            model.addAttribute("totalPrice", totalPrice);
            Integer totalPoints = cartService.getTotalPoints(cartList);
            model.addAttribute("totalPoints", totalPoints);
            Integer totalAmount = cartService.getTotalAmount(cartList);
            model.addAttribute("totalAmount", totalAmount);
            Map<String, List<CartListModel>> shopCartList = cartService.getShopCarts(cartList);
            model.addAttribute("shopCartList", shopCartList);
            return  "cart";
        }
    }

    @RequestMapping("/test")
    public String cart(Model model, @RequestParam("number") int number, HttpServletRequest request){
        System.out.println(number);

        //检查是否已经登陆
        User user = loginRegisterService.checkLoginStatus(request.getCookies());
        if(user == null) {
            model.addAttribute("user", new User());
            model.addAttribute("isSignin", false);
            return "signin";
        }
        else {
            model.addAttribute("isSignin", true);
            model.addAttribute("user", user);
            List<CartListModel> cartList = cartService.getCarts(user.getUid());
            BigDecimal totalPrice = cartService.getTotalPrice(cartList);
            model.addAttribute("totalPrice", totalPrice);
            Integer totalPoints = cartService.getTotalPoints(cartList);
            model.addAttribute("totalPoints", totalPoints);
            Integer totalAmount = cartService.getTotalAmount(cartList);
            model.addAttribute("totalAmount", totalAmount);
            Map<String, List<CartListModel>> shopCartList = cartService.getShopCarts(cartList);
            model.addAttribute("shopCartList", shopCartList);
            return "cart";
        }
    }
}
