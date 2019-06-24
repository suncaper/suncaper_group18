package org.group18.back.Controller;


import org.group18.back.Entity.User;
import org.group18.back.Entity.UserAddress;
import org.group18.back.Service.Impl.MyInfoServiceImpl;
import org.group18.back.Service.LoginRegisterService;
import org.group18.back.Service.MyInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
public class  MyInfoController {
    @Autowired
    MyInfoService myInfoService;
    @Autowired
    LoginRegisterService loginRegisterService;
    @RequestMapping("/myinfo")
    public String myinfo(Model model, HttpServletRequest request)
    {
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
            List<UserAddress> result = myInfoService.myaddress(user.getUid());
            model.addAttribute("address", result);
            return "myinfo";
        }
    }

}
