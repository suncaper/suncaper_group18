package org.group18.back.Controller;

import org.group18.back.Entity.User;
import org.group18.back.Service.LoginRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/bolvvv")
public class BolvvvController {
    @Autowired
    LoginRegisterService loginRegisterService;

    @RequestMapping("/shop")
    public String shop(Model model, @RequestParam("shopUid") String shopUid, HttpServletRequest request){
        //检查是否已经登陆
        User user = loginRegisterService.checkLoginStatus(request.getCookies());
        if(user == null) {
            model.addAttribute("user", new User());
            model.addAttribute("isSignin", false);
            return "index";
        }
        else {
            model.addAttribute("user", user);
            model.addAttribute("isSignin", true);
            //进行逻辑处理
            return null;
        }

    }
}
