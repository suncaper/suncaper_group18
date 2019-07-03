package org.group18.back.Controller;

import org.group18.back.Entity.User;
import org.group18.back.Model.ShopPageModel;
import org.group18.back.Service.StoreService;
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
public class StoreController {
    @Autowired
    LoginRegisterService loginRegisterService;

    @Autowired
    StoreService storeService;

    @RequestMapping("/getStoreList")
    public String getStoreList(Model model, HttpServletRequest request, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "searchKey", required = false) String searchKey){
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
        //设置每页显示数量为20
        int pageSize = 20;
        if(page == null) page = 1;//若未接受到页面请求，则设置为1
        //生成页面列表
        List<Integer> pagesNumberList = new ArrayList<>();
        int shopCount = storeService.getAllShopCount(searchKey);
        for(int i = 1; i <= (shopCount%pageSize==0?shopCount/pageSize:(shopCount/pageSize+1)); i++){
            pagesNumberList.add(i);
        }
        //设置搜索参数
        if(searchKey == null){
            model.addAttribute("searchKey", null);
        }
        else {
            model.addAttribute("searchKey", searchKey);
        }
        model.addAttribute("shopList", storeService.getShopList(pageSize, page, searchKey));
        model.addAttribute("pageNumberList", pagesNumberList);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageAmount", pagesNumberList.size());
        return "stores_01";
    }

    @RequestMapping("/shop")
    public String shop(Model model, @RequestParam("shopUid") String shopUid, HttpServletRequest request, @RequestParam(value = "page", required = false) Integer page){
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
        int goodsCount = storeService.getShopGoodsAmount(shopUid);
        //生成页面列表
        List<Integer> pagesNumberList = new ArrayList<>();
        for(int i = 1; i <= (goodsCount%pageSize == 0?goodsCount/pageSize:goodsCount/pageSize+1); i++){
            pagesNumberList.add(i);
        }
        //设置页面主要内容
        model.addAttribute("shopPageModel",storeService.getShopPageModel(Integer.valueOf(shopUid), pageSize, page));
        model.addAttribute("payWay", "money");//生成店铺页面时的商品信息为money支付
        model.addAttribute("pageNumberList", pagesNumberList);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageAmount", pagesNumberList.size());
        return "store_single_01";
    }
}
