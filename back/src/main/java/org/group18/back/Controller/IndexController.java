package org.group18.back.Controller;

import org.group18.back.Entity.Category;
import org.group18.back.Entity.Goods;
import org.group18.back.Entity.User;
import org.group18.back.Model.GoodsDeatilInfoModel;
import org.group18.back.Service.CartService;
import org.group18.back.Service.GoodsService;

import org.group18.back.Service.CategoryService;
import org.group18.back.Service.LoginRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
public class IndexController {
    @Autowired
    LoginRegisterService loginRegisterService;

    @Autowired
    CartService cartService;

    @Autowired
    CategoryService categoryService;

    @Resource
    private GoodsService goodsService;


//    测试index
    @RequestMapping("/index_test")
    public String index(Model model, HttpServletRequest request){
        Integer goods_uid = 1;
        Integer category_uid = 1;
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
        GoodsDeatilInfoModel goodsDeatilInfoModel = goodsService.getGoods(goods_uid);
        model.addAttribute("shops",goodsDeatilInfoModel);
        model.addAttribute("goodsReviews",goodsDeatilInfoModel);
        model.addAttribute("goodss",goodsDeatilInfoModel);

        List<Goods> recommendGoods = goodsDeatilInfoModel.getRecommendGoods();
        model.addAttribute("recommendGoodss",recommendGoods);

        List<Category> categoryList = categoryService.getCategorys(category_uid);
        model.addAttribute("CategoryList",categoryList);
        return "index";
    }

    //登陆请求
    @RequestMapping("/Signin_page")
    public String signinPage(Model model, HttpServletRequest request){
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
            return "index";
        }
    }



    //注册请求
    @RequestMapping("/Signup_page")
    public String signupPage(Model model, HttpServletRequest request){
        //检查是否已经登陆
        User user = loginRegisterService.checkLoginStatus(request.getCookies());
        if(user == null) {
            model.addAttribute("user", new User());
            model.addAttribute("isSignin", false);
            return "signup";
        }
        else {
            //设置登陆状态为false
            model.addAttribute("isSignin", true);
            model.addAttribute("user", user);
            return "index";
        }
    }

    //注册
    @RequestMapping("/Register")
    public String register(Model model, @ModelAttribute User user, HttpServletResponse httpServletResponse){
        Map<String, String> result = loginRegisterService.register(user.getUserName(), user.getPassWord(), user.getEmail());
        if(!result.containsKey("ticket")){
            model.addAttribute("isError", true);
            model.addAttribute("errorMsg", result.get("msg"));
            model.addAttribute("isSignin", false);
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
    @RequestMapping("/Signin")
    public String signin(Model model, @ModelAttribute User user, HttpServletResponse httpServletResponse){
        Map<String, String> result = loginRegisterService.login(user.getEmail(), user.getPassWord());
        if(!result.containsKey("ticket")){
            model.addAttribute("isError", true);
            model.addAttribute("errorMsg", result.get("msg"));
            model.addAttribute("isSignin", false);
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
    @RequestMapping("/Signout")
    public String signout(Model model, @ModelAttribute User user, HttpServletResponse httpServletResponse){
        Cookie cookie = new Cookie("ticket",null);
        cookie.setPath("/");
        httpServletResponse.addCookie(cookie);
        model.addAttribute("user", new User());//设置User信息为空
        model.addAttribute("isSignin", false);//设置登陆信息为false
        return "index";
    }

    //种类
    @RequestMapping("/StoreCategory")
    public String StoreCategory(Model model, HttpServletRequest request)
    {
        User user = loginRegisterService.checkLoginStatus(request.getCookies());
        if(user == null) {
            model.addAttribute("user", new User());
            model.addAttribute("isSignin", false);
        }
        else {
            model.addAttribute("isSignin", true);
            model.addAttribute("user", user);


        }
        return "StoreCategory";
    }
}
