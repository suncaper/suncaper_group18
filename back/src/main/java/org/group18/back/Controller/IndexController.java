package org.group18.back.Controller;

import org.group18.back.Entity.Category;
import org.group18.back.Entity.Goods;
import org.group18.back.Entity.Shop;
import org.group18.back.Entity.User;
import org.group18.back.Model.GoodsDeatilInfoModel;
import org.group18.back.Service.CartService;
import org.group18.back.Service.GoodsService;

import org.group18.back.Service.CategoryService;
import org.group18.back.Service.LoginRegisterService;
import org.group18.back.Service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
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

    @Autowired
    StoreService storeService;

    @Resource
    private GoodsService goodsService;

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

        List<Category> categoryList = categoryService.getIndexCategoryList();
        model.addAttribute("CategoryList",categoryList);

        List<Goods> indexGoods = goodsService.selectAllGoodsByClausedesc();
        model.addAttribute("indexgoods",indexGoods);


        List<Shop> indexShops = storeService.selectAllShop();
        model.addAttribute("indexshops",indexShops);

        return "index";
    }
}
