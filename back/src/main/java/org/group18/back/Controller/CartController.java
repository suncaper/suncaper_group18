package org.group18.back.Controller;

import org.group18.back.Entity.Cart;
import org.group18.back.Entity.User;
import org.group18.back.Service.CartService;
import org.group18.back.Service.LoginRegisterService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    LoginRegisterService loginRegisterService;

    @Resource
    private CartService cartService;

//    @RequestMapping(value = "/Cart")
//    public String cart(){
//        return "cart";
//    }

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
            List<Cart> cartList = cartService.getCarts(user.getUid());
            JSONArray jsonArray = new JSONArray(cartList);
            String carts = jsonArray.toString();
            return "cart";
        }
    }

    //商品加入购物车
    @RequestMapping("/addCart")
    public String addCart(Model model, HttpServletRequest request, int specification_uid, int goods_uid, int counts){
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
            Cart cart = cartService.getCart(user.getUid(), specification_uid);
            cartService.addAndUpdateCart(cart, user.getUid(), goods_uid, specification_uid, counts);
            return "deal_single";
        }
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
