package org.group18.back.Controller;

import org.group18.back.Entity.User;
import org.group18.back.Model.ShopPageModel;
import org.group18.back.Service.BolvvvService;
import org.group18.back.Service.LoginRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/bolvvv")
public class BolvvvController {
    @Autowired
    LoginRegisterService loginRegisterService;

    @Autowired
    BolvvvService bolvvvService;

    @RequestMapping("/getStoreList")
    public String getStoreList(Model model, HttpServletRequest request, @RequestParam(value = "page", required = false) Integer page){
        //检查是否已经登陆
        User user = loginRegisterService.checkLoginStatus(request.getCookies());
        if(user == null) {
            model.addAttribute("user", new User());
            model.addAttribute("isSignin", false);
        }
        else {
            model.addAttribute("user", user);
            model.addAttribute("isSignin", true);
        }
        //获取请求页数
        //设置每页显示数量为10
        int pageSize = 1;
        if(page == null) page = 1;//若未接受到页面请求，则设置为1
        //生成页面列表
        List<Integer> pagesNumberList = new ArrayList<>();
        for(int i = 1; i <= bolvvvService.getAllShopCount(); i++){
            pagesNumberList.add(i);
        }
        model.addAttribute("shopList", bolvvvService.getShopList(pageSize, page));
        model.addAttribute("pageNumberList", pagesNumberList);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageAmount", pagesNumberList.size());
        return "stores_01";
    }

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
            ShopPageModel shopPageModel = bolvvvService.getShopPageModel(Integer.valueOf(shopUid));
            if(shopPageModel == null) return "404";
            else {
                model.addAttribute(shopPageModel);
                return "store_single_01";
            }
        }

    }
}
