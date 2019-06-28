package org.group18.back.Controller;

import org.group18.back.Entity.User;
import org.group18.back.Entity.UserAddress;
import org.group18.back.Model.CartListModel;
import org.group18.back.Service.CartService;
import org.group18.back.Service.CheckoutService;
import org.group18.back.Service.LoginRegisterService;
import org.group18.back.Service.MyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class CheckoutController {
    @Autowired
    LoginRegisterService loginRegisterService;
    @Autowired
    CartService cartService;
    @Autowired
    CheckoutService checkoutService;
    @Autowired
    MyInfoService myInfoService;

    Integer addressId = 0;
    List<CartListModel> cartList = new ArrayList<>();

    @RequestMapping("/Checkout")
    public String checkout(Model model, HttpServletRequest request) {
        //检查是否已经登陆
        User user = loginRegisterService.checkLoginStatus(request.getCookies());
        if (user == null) {
            model.addAttribute("user", new User());
            model.addAttribute("isSignin", false);
            return "signin";
        } else {
            model.addAttribute("isSignin", true);
            model.addAttribute("user", user);
            cartList = cartService.getCarts(user.getUid());
            model.addAttribute("cartList", cartList);
            BigDecimal totalPrice = cartService.getTotalPrice(cartList);
            model.addAttribute("totalPrice", totalPrice);
            Integer totalPoints = cartService.getTotalPoints(cartList);
            model.addAttribute("totalPoints", totalPoints);
            Integer totalAmount = cartService.getTotalAmount(cartList);
            model.addAttribute("totalAmount", totalAmount);//购物车相关信息

            List<UserAddress> result = myInfoService.myaddress(user.getUid());
            if(result.isEmpty())
            {//如果result表为空,则不作显示
                model.addAttribute("isEmpty",true);
            }
            else
            {
                model.addAttribute("isEmpty",false);
                model.addAttribute("addressList", result);
                model.addAttribute("editAddress", new UserAddress());
            }
            return "checkout_billing";
        }
    }

    @RequestMapping("/checkout_single")
    public String checkout_single(Model model, HttpServletRequest request, @RequestParam("specification_uid") Integer specification_uid) {
        //检查是否已经登陆
        User user = loginRegisterService.checkLoginStatus(request.getCookies());
        if (user == null) {
            model.addAttribute("user", new User());
            model.addAttribute("isSignin", false);
            return "signin";
        } else {
            model.addAttribute("isSignin", true);
            model.addAttribute("user", user);
            //购物车相关信息
            cartList = cartService.getCart(user.getUid(), specification_uid);
            model.addAttribute("cartList", cartList);
            BigDecimal totalPrice = cartService.getTotalPrice(cartList);
            model.addAttribute("totalPrice", totalPrice);
            Integer totalPoints = cartService.getTotalPoints(cartList);
            model.addAttribute("totalPoints", totalPoints);
            Integer totalAmount = cartService.getTotalAmount(cartList);
            model.addAttribute("totalAmount", totalAmount);
            List<UserAddress> result = myInfoService.myaddress(user.getUid());
            if(result.isEmpty())
            {//如果result表为空,则不作显示
                model.addAttribute("isEmpty",true);
            }
            else
            {
                model.addAttribute("isEmpty",false);
                model.addAttribute("addressList", result);
                model.addAttribute("editAddress", new UserAddress());
            }
            return "checkout_billing";
        }
    }

    @RequestMapping("/checkout_method")
    public String checkoutMethod(Model model, HttpServletRequest request, @RequestParam("address_id") Integer address_id) {
        //检查是否已经登陆
        User user = loginRegisterService.checkLoginStatus(request.getCookies());
        if (user == null) {
            model.addAttribute("user", new User());
            model.addAttribute("isSignin", false);
            return "signin";
        } else {
            model.addAttribute("isSignin", true);
            model.addAttribute("user", user);
//            List<CartListModel> cartList = cartService.getCarts(user.getUid());
            model.addAttribute("cartList", cartList);
            BigDecimal totalPrice = cartService.getTotalPrice(cartList);
            model.addAttribute("totalPrice", totalPrice);
            Integer totalPoints = cartService.getTotalPoints(cartList);
            model.addAttribute("totalPoints", totalPoints);
            Integer totalAmount = cartService.getTotalAmount(cartList);
            model.addAttribute("totalAmount", totalAmount);//购物车相关信息
            BigDecimal balance = checkoutService.getUserBalance(user.getUid());//账户余额
            model.addAttribute("balance", balance);
            Integer points = checkoutService.getUserPoints(user.getUid());//账户积分
            model.addAttribute("points", points);
            addressId = address_id;//存储地址信息
            System.out.println(addressId);
            return "checkout_method";
        }
    }

    @RequestMapping("/checkout_commit")
    public String checkoutCommit(Model model, HttpServletRequest request){
        //检查是否已经登陆
        User user = loginRegisterService.checkLoginStatus(request.getCookies());
        if (user == null) {
            model.addAttribute("user", new User());
            model.addAttribute("isSignin", false);
            return "signin";
        } else {
            String checkout_method;
            model.addAttribute("isSignin", true);
            model.addAttribute("user", user);
//            List<CartListModel> cartList = cartService.getCarts(user.getUid());
            model.addAttribute("cartList", cartList);
            Map<String, List<CartListModel>> shopCartList = cartService.getShopCarts(cartList);
            model.addAttribute("shopCartList", shopCartList);//商店-购物车信息
            BigDecimal totalPrice = cartService.getTotalPrice(cartList);
            model.addAttribute("totalPrice", totalPrice);//总价
            Integer totalPoints = cartService.getTotalPoints(cartList);
            model.addAttribute("totalPoints", totalPoints);//总积分
            Integer totalAmount = cartService.getTotalAmount(cartList);
            model.addAttribute("totalAmount", totalAmount);//购物车相关信息
            BigDecimal balance = checkoutService.getUserBalance(user.getUid());
            model.addAttribute("balance", balance);//账户余额
            Integer points = checkoutService.getUserPoints(user.getUid());
            model.addAttribute("points", points);//账户积分
            model.addAttribute("address_id", addressId); //地址信息
            Integer result = checkoutService.payment(user.getUid(), totalPrice, totalPoints, balance, points, request.getParameter("checkout_method"), shopCartList, cartList);
            checkout_method = request.getParameter("checkout_method");
            if (result != 2){
                checkoutService.generateOrder(user.getUid(), request.getParameter("checkout_method"), shopCartList, addressId, result);
                return "myorder";
            }
            else{
                System.out.println(result);
                List<CartListModel> cartList = cartService.getCarts(user.getUid());
                shopCartList = cartService.getShopCarts(cartList);
                model.addAttribute("shopCartList", shopCartList);
                return "cart";
            }
        }
    }
}
