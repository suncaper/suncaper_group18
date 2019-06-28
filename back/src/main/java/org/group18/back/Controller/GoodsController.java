package org.group18.back.Controller;

import org.group18.back.Entity.Cart;
import org.group18.back.Entity.Goods;
import org.group18.back.Entity.User;
import org.group18.back.Model.CartListModel;
import org.group18.back.Model.GoodsDeatilInfoModel;
import org.group18.back.Service.CartService;
import org.group18.back.Service.GoodsService;
import org.group18.back.Service.LoginRegisterService;
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
import java.util.List;

@Controller
public class GoodsController {
    @Autowired
    LoginRegisterService loginRegisterService;

    @Autowired
    CartService cartService;

    @Resource
    private GoodsService goodsService;

    @RequestMapping("/goods_uid")
    public String goods(Model model, HttpServletRequest request){
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
        return "goods";
    }

    public String getGood(Model model,HttpServletRequest request,int goods_uid){
        User user = loginRegisterService.checkLoginStatus(request.getCookies());
        if(user == null) {
            model.addAttribute("user", new User());
            model.addAttribute("isSignin", false);
        }
        else {
            model.addAttribute("isSignin", true);
            model.addAttribute("user", user);

        }
        Goods goods = goodsService.getGood(goods_uid);
        model.addAttribute("goods",goods);


        return "goods";
    }

    public String getGoods(Model model, HttpServletRequest request,int goods_uid, String sellerUid){
        User user = loginRegisterService.checkLoginStatus(request.getCookies());
        if(user == null) {
            model.addAttribute("user", new User());
            model.addAttribute("isSignin", false);
        }
        else {
            model.addAttribute("isSignin", true);
            model.addAttribute("user", user);

        }
        GoodsDeatilInfoModel goodsDeatilInfoModel = goodsService.getGoods(goods_uid,sellerUid);
        model.addAttribute("shop",goodsDeatilInfoModel);
        model.addAttribute("goodsReview",goodsDeatilInfoModel);
        model.addAttribute("goods",goodsDeatilInfoModel);

        List<Goods> recommendGoods = goodsDeatilInfoModel.getRecommendGoods();
        model.addAttribute("recommendGoods",recommendGoods);

        return "goods";
    }


   /* @RequestMapping(value = "/getReview", method = RequestMethod.POST)
    public String getReview(int goods_uid){
        List<Goods> reviewList = goodsService.getReviews(goods_uid);
        JSONArray jsonArray = new JSONArray(reviewList);
        String reviews = jsonArray.toString();
        return "goods";
    }*/


//    @RequestMapping(value = "/GoodsaddCart", method = RequestMethod.POST)
//    @ResponseBody
//    public String addCart(String user_uid, int goods_uid, int specification_uid, int counts){
//        Cart cart = cartService.getCart(user_uid, specification_uid);
//        cartService.addAndUpdateCart(cart, user_uid, goods_uid, specification_uid, counts);
//        return "goods";
//    }


}
