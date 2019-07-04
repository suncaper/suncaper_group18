package org.group18.back.Controller;

import org.group18.back.Entity.*;
import org.group18.back.Model.GoodsManagementModel;
import org.group18.back.Model.OrderPageModel;
import org.group18.back.Model.ShopPageModel;
import org.group18.back.Service.CategoryService;
import org.group18.back.Service.LoginRegisterService;
import org.group18.back.Service.MyInfoService;
import org.group18.back.Service.MystoreService;
import org.group18.back.Service.StoreService;
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
        //设置每页显示数量为8
        int pageSize = 8;
        if(page == null) page = 1;//若未接受到页面请求，则设置为1
        Integer shopUid = mystoreService.getShopUid(user.getUid());
        int goodsCount = storeService.getShopGoodsAmount(String.valueOf(shopUid));
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
        return "mystore";
    }

    @GetMapping("/editGoods")
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
        model.addAttribute("goods_uid", goods_uid);
        System.out.println(goods_uid);
        GoodsManagementModel goodsManagementModel = mystoreService.getGoodsDetails(goods);
        goodsManagementModel.setGoods_uid(goods_uid);
        System.out.println(goodsManagementModel.getGoods_uid());
        model.addAttribute("goodsManagementModel", goodsManagementModel);
        return "edit_goods";
    }

    @PostMapping("/comfirmEdit")
    public String comfirmEdit(Model model, HttpServletRequest request, @RequestParam("goods_uid") Integer goods_uid, @RequestParam(value = "page", required = false) Integer page, GoodsManagementModel goodsManagementModel, @RequestParam("imgfile")MultipartFile file, @RequestParam("detail_img_file")MultipartFile detail_img_file){
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
        System.out.println(goods_uid);
        mystoreService.editGoods(goodsManagementModel, goods_uid, file, detail_img_file);
        //进行逻辑处理
        //设置翻页
        //设置每页显示数量为8
        int pageSize = 8;
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
                return "redirect:/MyStore/mystore";
        }
    }

    @RequestMapping("/editGoodsSpecification")
    public String editGoodsSpecification(Model model, HttpServletRequest request, @RequestParam("goods_uid") Integer goods_uid){
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
        model.addAttribute("goods_uid", goods_uid);
        List<GoodsSpecification> goodsSpecificationList = mystoreService.getGoodsSpecification(goods_uid);
        model.addAttribute("goodsSpecificationList", goodsSpecificationList);
        GoodsSpecification newgoodsSpecifications = new GoodsSpecification();
        newgoodsSpecifications.setGoodsUid(goods_uid);
        model.addAttribute("newgoodsSpecifications", newgoodsSpecifications);
        return "edit_goods_specification";
    }

    @RequestMapping("/editSpecification")
    public String editSpecification(GoodsSpecification goodsSpecification){
        mystoreService.editSpecification(goodsSpecification);
        return "redirect:/MyStore/editGoodsSpecification?" + "goods_uid=" + goodsSpecification.getGoodsUid().toString();
    }

    @RequestMapping("/deleteGoods")
    public String deleteGoods(Model model, HttpServletRequest request, @RequestParam("goods_uid") Integer goods_uid){
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
        boolean success = mystoreService.deleteGoods(goods);

        return "redirect:/MyStore/mystore";
    }

    @RequestMapping("/deleteSpecification")
    public String deleteSpecification(@RequestParam("specification_uid") Integer specification_uid){
       GoodsSpecification goodsSpecification = mystoreService.getSpecification(specification_uid);
        mystoreService.deleteSpecification(goodsSpecification);
        return "redirect:/MyStore/editGoodsSpecification?" + "goods_uid=" + goodsSpecification.getGoodsUid().toString();
    }

    @RequestMapping("/addSpecification")
    public String addSpecification(GoodsSpecification newgoodsSpecification){
       mystoreService.addSpecification(newgoodsSpecification.getGoodsUid(), newgoodsSpecification);
       return "redirect:/MyStore/editGoodsSpecification?" + "goods_uid=" + newgoodsSpecification.getGoodsUid();
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
        return "add_goods";
    }

    @PostMapping("/comfirmAdd")
    public String comfirmAdd(Model model, HttpServletRequest request,  @RequestParam(value = "page", required = false) Integer page, GoodsManagementModel goodsManagementModel, @RequestParam("imgfile")MultipartFile file, @RequestParam("detail_img_file")MultipartFile detail_img_file){
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
        Map<String, String> result = mystoreService.addGoods(goodsManagementModel, user.getUid(), file, detail_img_file);
        //进行逻辑处理
        //设置翻页
        //设置每页显示数量为8
        int pageSize = 8;
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
            if(result.get("msg").equals("该商品已存在，且规格名称不能重复")){
                model.addAttribute("errorMsg", result.get("msg"));
                model.addAttribute("isError", true);
                List<Category> categoryList = mystoreService.getAllCategory();
                System.out.println(categoryList);
                model.addAttribute("categoryList", categoryList);
                return "add_goods";
            }
            else{
                return "redirect:/MyStore/mystore";
            }
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

    @RequestMapping("/deleteOrder")
    public String deleteOrder(@RequestParam("orderId") Integer orderId){
        System.out.println("执行到此");
        mystoreService.deleteSellerOrder(orderId);
        return "redirect:/MyStore/myorder";
    }
    @RequestMapping("/agreeRefundOrder")
    public String agreeRefundOrder(@RequestParam("orderId") Integer orderId){
        mystoreService.agreeRefundOrder(orderId);
        return "redirect:/MyStore/myorder";
    }
    @RequestMapping("/refuseRefundOrder")
    public String refuseRefundOrder(@RequestParam("orderId") Integer orderId){
        mystoreService.refuseRefundOrder(orderId);
        return "redirect:/MyStore/myorder";
    }
    @RequestMapping("/mailGoods")
    public String mailGoods(@RequestParam("orderId") Integer orderId){
        mystoreService.mailGoods(orderId);
        return "redirect:/MyStore/myorder";
    }
}
