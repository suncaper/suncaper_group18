package org.group18.back.Controller;


import org.group18.back.Entity.User;
import org.group18.back.Service.LoginRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class LoginRegisterController {
    @Autowired
    LoginRegisterService loginRegisterService;

    @RequestMapping("/index")
    public String index(Model model, HttpServletRequest request){
        //检查是否已经登陆
        User user = loginRegisterService.checkLoginStatus(request.getCookies());
        if(user == null) {
            model.addAttribute("user", new User());
            model.addAttribute("isSignin", false);
        }
        else {
            model.addAttribute("isSignin", true);
            model.addAttribute("user", user);
        }
        return "index";
    }

    //登陆请求
    @RequestMapping("/signin_page")
    public String signinPage(Model model, HttpServletRequest request){
        //检查是否已经登陆
        User user = loginRegisterService.checkLoginStatus(request.getCookies());
        if(user == null) {
            model.addAttribute("user", new User());
            return "signin";
        }
        else return "index";
    }

    //注册请求
    @RequestMapping("/signup_page")
    public String signupPage(Model model, HttpServletRequest request){
        //检查是否已经登陆
        User user = loginRegisterService.checkLoginStatus(request.getCookies());
        if(user == null) {
            model.addAttribute("user", new User());
            return "signup";
        }
        else {
            //设置登陆状态为false
            model.addAttribute("isSignin", false);
            return "index";
        }
    }

    //注册
    @RequestMapping("/register")
    public String register(Model model, @ModelAttribute User user, HttpServletResponse httpServletResponse){
        Map<String, String> result = loginRegisterService.register(user.getName(), user.getPassWord(), user.getEmail());
        if(!result.containsKey("ticket")){
            model.addAttribute("isError", true);
            model.addAttribute("errorMsg", result.get("msg"));
            return "signup";
        }
        else {
            Cookie cookie = new Cookie("ticket",result.get("ticket"));
            cookie.setPath("/");
            httpServletResponse.addCookie(cookie);
            model.addAttribute("isSignin", true);
            model.addAttribute("user", loginRegisterService.getUserInfoByTicket(result.get("ticket")));
            return "index";
        }
    }

    //登陆
    @RequestMapping("/signin")
    public String signin(Model model, @ModelAttribute User user, HttpServletResponse httpServletResponse){
        Map<String, String> result = loginRegisterService.login(user.getEmail(), user.getPassWord());
        if(!result.containsKey("ticket")){
            model.addAttribute("isError", true);
            model.addAttribute("errorMsg", result.get("msg"));
            return "signin";
        }
        else {
            Cookie cookie = new Cookie("ticket",result.get("ticket"));
            cookie.setPath("/");
            httpServletResponse.addCookie(cookie);
            model.addAttribute("isSignin", true);
            model.addAttribute("user", loginRegisterService.getUserInfoByTicket(result.get("ticket")));
            return "index";
        }
    }

    //登出
    @RequestMapping("/signout")
    public String signout(Model model, @ModelAttribute User user, HttpServletResponse httpServletResponse){
        Cookie cookie = new Cookie("ticket",null);
        cookie.setPath("/");
        httpServletResponse.addCookie(cookie);
        model.addAttribute("user", new User());//设置User信息为空
        model.addAttribute("isSignin", false);//设置登陆信息为false
        return "index";
    }
}
