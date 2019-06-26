package org.group18.back.Controller;

import org.group18.back.Entity.User;
import org.group18.back.Entity.UserAddress;
import org.group18.back.Model.CartListModel;
import org.group18.back.Service.CartService;
import org.group18.back.Service.CheckoutService;
import org.group18.back.Service.LoginRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class CheckoutController {
    @Autowired
    LoginRegisterService loginRegisterService;
    @Autowired
    CartService cartService;
    @Autowired
    CheckoutService checkoutService;

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
            List<CartListModel> cartList = cartService.getCarts(user.getUid());
            model.addAttribute("cartList", cartList);
            BigDecimal totalPrice = cartService.getTotalPrice(cartList);
            model.addAttribute("totalPrice", totalPrice);
            Integer totalPoints = cartService.getTotalPoints(cartList);
            model.addAttribute("totalPoints", totalPoints);
            Integer totalAmount = cartService.getTotalAmount(cartList);
            model.addAttribute("totalAmount", totalAmount);//购物车相关信息

            List<UserAddress> userAddressList = checkoutService.getUserAddress(user.getUid());//收货信息
            model.addAttribute("userAddressList", userAddressList);
            if(userAddressList.isEmpty())
                model.addAttribute("isEmpty",true);
            else
                model.addAttribute("isEmpty",false);
            
            return "checkout_billing";
        }
    }
}
