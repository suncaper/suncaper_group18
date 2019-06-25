package org.group18.back.Controller;

import org.group18.back.Entity.User;
import org.group18.back.Model.CartListModel;
import org.group18.back.Service.LoginRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

public class CheckoutController {
    @Autowired
    LoginRegisterService loginRegisterService;

    @RequestMapping("/checkout_method")
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
            return "checkout_method";
        }
    }
}
