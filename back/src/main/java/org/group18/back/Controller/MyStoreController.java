package org.group18.back.Controller;

import org.group18.back.Entity.Category;
import org.group18.back.Entity.Goods;
import org.group18.back.Entity.User;
import org.group18.back.Model.GoodsManagementModel;
import org.group18.back.Model.ShopPageModel;
import org.group18.back.Service.Impl.CategoryService;
import org.group18.back.Service.LoginRegisterService;
import org.group18.back.Service.MystoreService;
import org.group18.back.Service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/MyStore")
public class MyStoreController {
    @Autowired
    LoginRegisterService loginRegisterService;
    @Autowired
    MystoreService mystoreService;
    @Autowired
    StoreService storeService;
    @Autowired
    CategoryService categoryService;

    @RequestMapping("/mystore")
    public String mystore(Model model, HttpServletRequest request, @RequestParam(value = "page", required = false) Integer page){
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
        //进行逻辑处理
        //设置翻页
        //设置每页显示数量为6
        int pageSize = 6;
        if(page == null) page = 1;//若未接受到页面请求，则设置为1
        Integer shopUid = mystoreService.getShopUid(user.getUid());
        ShopPageModel shopPageModel = storeService.getShopPageModel(shopUid, pageSize, page);
        if(shopPageModel == null) return "404";
        else {
            //设置页面主要内容
            model.addAttribute("shopPageModel",shopPageModel);
            //生成页面列表
            List<Integer> pagesNumberList = new ArrayList<>();
            for(int i = 1; i <= (shopPageModel.getShopGoodsList().size()%pageSize == 0?shopPageModel.getShopGoodsList().size()/pageSize:shopPageModel.getShopGoodsList().size()/pageSize+1); i++){
                pagesNumberList.add(i);
            }
            model.addAttribute("pageNumberList", pagesNumberList);
            model.addAttribute("currentPage", page);
            model.addAttribute("pageAmount", pagesNumberList.size());
            return "mystore";
        }
    }

    @RequestMapping("/editGoods")
    public String editGoods(Model model, HttpServletRequest request, @RequestParam("goods_uid") Integer goods_uid){
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
        model.addAttribute("goods_uid", goods_uid);
        Goods goods = mystoreService.getGoods(goods_uid);
        model.addAttribute("goods", goods);
        List<Category> categoryList = mystoreService.getAllCategory();
        System.out.println(categoryList);
        model.addAttribute("categoryList", categoryList);
        return "checkout_payment";
    }

    @GetMapping("/addGoods")
    public String addGoods(Model model, HttpServletRequest request){
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
        List<Category> categoryList = mystoreService.getAllCategory();
        System.out.println(categoryList);
        model.addAttribute("categoryList", categoryList);
        return "checkout_payment";
    }

    @PostMapping("/comfirmAdd")
    public String comfirmAdd(Model model, HttpServletRequest request,  @RequestParam(value = "page", required = false) Integer page, GoodsManagementModel goodsManagementModel){
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
        Map<String, String> result = mystoreService.addGoods(goodsManagementModel, user.getUid());
        //进行逻辑处理
        //设置翻页
        //设置每页显示数量为6
        int pageSize = 6;
        if(page == null) page = 1;//若未接受到页面请求，则设置为1
        Integer shopUid = mystoreService.getShopUid(user.getUid());
        ShopPageModel shopPageModel = storeService.getShopPageModel(shopUid, pageSize, page);
        if(shopPageModel == null) return "404";
        else {
            //设置页面主要内容
            model.addAttribute("shopPageModel", shopPageModel);
            //生成页面列表
            List<Integer> pagesNumberList = new ArrayList<>();
            for (int i = 1; i <= (shopPageModel.getShopGoodsList().size() % pageSize == 0 ? shopPageModel.getShopGoodsList().size() / pageSize : shopPageModel.getShopGoodsList().size() / pageSize + 1); i++) {
                pagesNumberList.add(i);
            }
            model.addAttribute("pageNumberList", pagesNumberList);
            model.addAttribute("currentPage", page);
            model.addAttribute("pageAmount", pagesNumberList.size());
            return "mystore";
        }
    }
}
