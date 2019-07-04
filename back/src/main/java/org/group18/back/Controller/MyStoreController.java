package org.group18.back.Controller;

import org.group18.back.Entity.Category;
import org.group18.back.Entity.Goods;
import org.group18.back.Entity.Shop;
import org.group18.back.Entity.User;
import org.group18.back.Model.GoodsManagementModel;
import org.group18.back.Model.OrderPageModel;
import org.group18.back.Model.ShopPageModel;
import org.group18.back.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    @Autowired
    MyInfoService myInfoService;
    @Autowired
    SellerService sellerService;

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

    @RequestMapping("/mystoreinfo")
    public String shopinfo(Model model, HttpServletRequest request)
    {
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

            Shop shop = mystoreService.getMyStoreInfo(user.getUid());
            model.addAttribute("shop",shop);
            return "mystoreinfo";
        }
    }
    @RequestMapping("/shop_name_edit")
    public String editshopname(Model model, HttpServletRequest request,@RequestParam("name")String name)
    {
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

            Shop shop = new Shop();
            shop.setSellerUid(user.getUid());
            shop.setShopName(name);
            Map<String,String> result = sellerService.updateshopinfo(shop);
            shop = mystoreService.getMyStoreInfo(user.getUid());
            model.addAttribute("shop",shop);
            if(result.containsKey("error"))
            {
                model.addAttribute("isError",true);
                model.addAttribute("msg",result.get("error"));
            }
            else if(result.containsKey("success")){
                model.addAttribute("success",true);
                model.addAttribute("msg",result.get("success"));
            }
            return "mystoreinfo";
        }
    }
    @RequestMapping("/shop_address_edit")
    public String editshopaddress(Model model, HttpServletRequest request,@RequestParam("address")String address)
    {
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

            Shop shop = new Shop();
            shop.setSellerUid(user.getUid());
            shop.setAddress(address);
            Map<String,String> result = sellerService.updateshopinfo(shop);
            shop = mystoreService.getMyStoreInfo(user.getUid());
            model.addAttribute("shop",shop);
            if(result.containsKey("error"))
            {
                model.addAttribute("isError",true);
                model.addAttribute("msg",result.get("error"));
            }
            else if(result.containsKey("success")){
                model.addAttribute("success",true);
                model.addAttribute("msg",result.get("success"));
            }
            return "mystoreinfo";
        }
    }
    @RequestMapping("/shop_describes_edit")
    public String editshopdescribes(Model model, HttpServletRequest request,@RequestParam("describes")String describes)
    {
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

            Shop shop = new Shop();
            shop.setSellerUid(user.getUid());
            shop.setDescribes(describes);
            Map<String,String> result = sellerService.updateshopinfo(shop);
            shop = mystoreService.getMyStoreInfo(user.getUid());
            model.addAttribute("shop",shop);

            if(result.containsKey("error"))
            {
                model.addAttribute("isError",true);
                model.addAttribute("msg",result.get("error"));
            }
            else if(result.containsKey("success")){
                model.addAttribute("success",true);
                model.addAttribute("msg",result.get("success"));
            }
            return "mystoreinfo";
        }
    }
    @RequestMapping("/shop_img_edit")
    public String editshopimg(Model model, HttpServletRequest request,@RequestParam("img")MultipartFile img)
    {
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

            Shop shop = mystoreService.getMyStoreInfo(user.getUid());

            Map<String,String> result = sellerService.updateshopimg(shop,img);

            shop = mystoreService.getMyStoreInfo(user.getUid());

            model.addAttribute("shop",shop);
            if(result.containsKey("error"))
            {
                model.addAttribute("isError",true);
                model.addAttribute("msg",result.get("error"));
            }
            else if(result.containsKey("success")){
                model.addAttribute("success",true);
                model.addAttribute("msg",result.get("success"));
            }
            return "mystoreinfo";
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

    //卖家订单管理
    @RequestMapping("/myorder")
    public String myorder(Model model, HttpServletRequest request, @RequestParam(value = "page", required = false) Integer page){
        //检查是否登陆
        User user = loginRegisterService.checkLoginStatus(request.getCookies());
        if (user == null) {
            model.addAttribute("user", new User());
            model.addAttribute("isSignin", false);
            return "signin";

        } else {
            model.addAttribute("user", user);
            model.addAttribute("isSignin", true);

            if(!user.getIsSeller()) return "index";//当检测到非卖家时跳转至主页面
            else {
                //获取请求页数
                //设置每页显示数量为5
                int pageSize = 5;
                if (page == null) page = 1;//若未接受到页面请求，则设置为1
                //生成页面列表
                List<Integer> pagesNumberList = new ArrayList<>();
                long count = mystoreService.getSellerOrderCount(user.getUid());
                for (int i = 1; i <= (count % pageSize == 0 ? count / pageSize : (count / pageSize + 1)); i++) {
                    pagesNumberList.add(i);
                }
                List<OrderPageModel> orderPageModels = myInfoService.getOrderPageInfo(null, user.getUid(), pageSize, page);
                model.addAttribute("pageNumberList", pagesNumberList);
                model.addAttribute("currentPage", page);
                model.addAttribute("pageAmount", pagesNumberList.size());
                model.addAttribute("orderPageList", orderPageModels);
                return "seller_order";
            }
        }

    }
}