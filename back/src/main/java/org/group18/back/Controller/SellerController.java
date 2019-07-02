package org.group18.back.Controller;

import com.sun.org.apache.bcel.internal.generic.MULTIANEWARRAY;
import org.apache.ibatis.annotations.Param;
import org.group18.back.Dao.*;
import org.group18.back.Entity.*;
import org.group18.back.Model.HistroyGoodsModel;
import org.group18.back.Model.OrderPageModel;
import org.group18.back.Model.PointsShopModel;
import org.group18.back.Service.Impl.MyInfoServiceImpl;
import org.group18.back.Service.LoginRegisterService;
import org.group18.back.Service.MyInfoService;
import org.group18.back.Service.PointsService;
import org.group18.back.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.EditorKit;
import java.io.File;
import java.util.*;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class SellerController {
    @Autowired
    LoginRegisterService loginRegisterService;
    @Autowired
    SellerService sellerService;

    @RequestMapping("/become_seller")
    public String becomeSeller(Model model, HttpServletRequest request) {
        //检查是否已经登陆
        User user = loginRegisterService.checkLoginStatus(request.getCookies());
        if (user == null) {
            model.addAttribute("user", new User());
            model.addAttribute("isSignin", false);
            return "signin";
        }
        else
        {
            if(user.getIsSeller()==true)
            {
                return "index";
            }
            else
            {
                Shop shop = new Shop();
                model.addAttribute("isSignin", true);
                model.addAttribute("user", user);
                model.addAttribute("shop",shop);
                return "become_seller";
            }
        }
    }

    @RequestMapping("/seller_submit")
    public String sellerSubmit(Model model, @ModelAttribute Shop shop, HttpServletRequest request, @RequestParam("file")MultipartFile file) //还差图片上传
    {
        User user = loginRegisterService.checkLoginStatus(request.getCookies());
        if (user == null) {
            model.addAttribute("user", new User());
            model.addAttribute("isSignin", false);
            return "signin";
        }
        else
        {
            model.addAttribute("isSignin", true);
            model.addAttribute("user", user);

            shop.setSellerUid(user.getUid());
            shop.setType(2);
            shop.setImgUrl("111");                  //暂时没有imgurl，随便设置的测试用。
            Map<String,String> result = sellerService.newSellerShop(shop, file);
            user.setIsSeller(true);

            if(result.containsKey("error"))
            {
                model.addAttribute("isError",true);
                model.addAttribute("errorMsg",result.get("error"));
                return "become_seller";
            }
            else
            {
                model.addAttribute("isSuccess",true);
                return "redirect:/index";
            }
        }
    }
}