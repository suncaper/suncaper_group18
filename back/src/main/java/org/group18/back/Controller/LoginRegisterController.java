package org.group18.back.Controller;


import org.group18.back.Entity.User;
import org.group18.back.Service.LoginRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class LoginRegisterController {
    @Autowired
    LoginRegisterService loginRegisterService;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    //登陆请求
    @RequestMapping("/signin_page")
    public String signinPage(Model model, HttpServletRequest request){
        //检查是否已经登陆
        Cookie[] cookies =  request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("ticket")){
                    //此时证明用户已有本网站的ticket，因此查询数据库获取用户信息
                    User user = loginRegisterService.judgeUserLoginStatus(cookie.getValue());
                    if(user == null) {
                        User user1 = new User();
                        model.addAttribute("user", user1);
                        return "signin";
                    }
                    else return "index";//TODO：需要添加前端页面修改逻辑
                }
            }
        }
        return "signin";
    }

    //注册请求
    @RequestMapping("/signup_page")
    public String signupPage(Model model, HttpServletRequest request){
        //检查是否已经登陆
        Cookie[] cookies =  request.getCookies();
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("ticket")){
                    //此时证明用户已有本网站的ticket，因此查询数据库获取用户信息
                    User user = loginRegisterService.judgeUserLoginStatus(cookie.getValue());
                    if(user == null) {
                        User user1 = new User();
                        model.addAttribute("user",user1);
                        return "signup";
                    }
                    else return "index";//TODO：需要添加前端页面修改逻辑
                }
            }
        }
        User user2 = new User();
        model.addAttribute("user",user2);
        return "signup";
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
            return "index";
        }
    }

    //登出
    @RequestMapping("/signout")
    public String signout(Model model, @ModelAttribute User user, HttpServletResponse httpServletResponse){
        Cookie cookie = new Cookie("ticket",null);
        cookie.setPath("/");
        httpServletResponse.addCookie(cookie);
        return "index";
    }
}
