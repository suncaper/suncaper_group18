package org.group18.back.Controller;

import org.apache.ibatis.annotations.Param;
import org.group18.back.Entity.*;
import org.group18.back.Model.CartListModel;
import org.group18.back.Model.GoodsDeatilInfoModel;
import org.group18.back.Service.CartService;
import org.group18.back.Service.GoodsService;
import org.group18.back.Service.LoginRegisterService;
import org.group18.back.Service.MyInfoService;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GoodsController {
    @Autowired
    LoginRegisterService loginRegisterService;

    @Autowired
    CartService cartService;

    @Autowired
    MyInfoService myInfoService;

    @Resource
    private GoodsService goodsService;

    @RequestMapping("/getGoods")
    public String getGoods(Model model, HttpServletRequest request, @RequestParam("goodsUid") Integer goodsUid, @RequestParam("payWay") String payWay){
        //payWay有两种：money和points
        User user = loginRegisterService.checkLoginStatus(request.getCookies());
        if(user == null) {
            model.addAttribute("user", new User());
            model.addAttribute("isSignin", false);
        }
        else {
            model.addAttribute("isSignin", true);
            model.addAttribute("user", user);
            //添加足迹信息
            myInfoService.addUserHistory(user.getUid(), goodsUid);
        }

        GoodsDeatilInfoModel goodsDeatilInfoModel = goodsService.getGoods(goodsUid);
        if(goodsDeatilInfoModel == null) {
            model.addAttribute("isEmpty", true);
        }
        else {
            if(!goodsDeatilInfoModel.getGoods().getIsExchange()){
                model.addAttribute("payWay", "money");//由于商品不支持积分兑换，故默认返回人名币支付页面
            }
            else {
                model.addAttribute("payWay", payWay);
            }
            model.addAttribute("isEmpty", false);
            model.addAttribute("goodsInfo", goodsDeatilInfoModel);
        }
        return "goods";
    }

    @RequestMapping(value = "/addGoodsToCart")
    public String addCart(HttpServletRequest request, @RequestParam("goodsUid") Integer goodsUid, @RequestParam("specificationUid") Integer specificationUid, @RequestParam("counts") Integer counts, @RequestParam("payWay") String payWay){
        System.out.println("添加商品");
        User user = loginRegisterService.checkLoginStatus(request.getCookies());
        if(user != null){
            goodsService.addGoodsToCart(user.getUid(), goodsUid, specificationUid, counts, payWay);
            return "redirect:/Cart";
        }
        else {
            return "redirect:/signin_page";
        }
    }

    @RequestMapping(value = "/getGoodsList")
    public String getGoodsList(Model model, HttpServletRequest request, @RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "searchKey", required = false) String searchKey){
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
        //设置每页显示数量为12
        int pageSize = 12;
        if(page == null) page = 1;//若未接受到页面请求，则设置为1
        //生成页面列表
        List<Integer> pagesNumberList = new ArrayList<>();
        int goodsCount = goodsService.getGoodsSearchAmount(searchKey);
        for(int i = 1; i <= (goodsCount%pageSize==0?goodsCount/pageSize:(goodsCount/pageSize+1)); i++){
            pagesNumberList.add(i);
        }
        //设置搜索参数
        if(searchKey == null){
            model.addAttribute("searchKey", null);
        }
        else {
            model.addAttribute("searchKey", searchKey);
        }
        model.addAttribute("goodsList", goodsService.getGoodsSearchList(pageSize, page, searchKey));
        model.addAttribute("payWay", "money");
        model.addAttribute("pageNumberList", pagesNumberList);
        model.addAttribute("currentPage", page);
        model.addAttribute("pageAmount", pagesNumberList.size());
        return "goods_search";
    }

}
