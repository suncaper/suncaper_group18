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
        User user = loginRegisterService.checkLoginStatus(request.getCookies());
        if(user != null){
            goodsService.addGoodsToCart(user.getUid(), goodsUid, specificationUid, counts, payWay);
            return "redirect:/getGoods?goodsUid="+goodsUid+"&payWay="+payWay;
        }
        else {
            return "redirect:/signin_page";
        }
    }

}
