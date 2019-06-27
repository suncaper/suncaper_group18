package org.group18.back.Controller;


import org.apache.ibatis.annotations.Param;
import org.group18.back.Dao.*;
import org.group18.back.Entity.UserAddress;
import org.group18.back.Entity.User;
import org.group18.back.Entity.UserHistory;
import org.group18.back.Entity.Goods;
import org.group18.back.Model.HistroyGoodsModel;
import org.group18.back.Model.OrderPageModel;
import org.group18.back.Service.Impl.MyInfoServiceImpl;
import org.group18.back.Service.LoginRegisterService;
import org.group18.back.Service.MyInfoService;
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
import java.util.*;

@Controller
public class  MyInfoController {
    @Autowired
    MyInfoService myInfoService;
    @Autowired
    LoginRegisterService loginRegisterService;

    @RequestMapping("/myinfo")
    public String myinfo(Model model, HttpServletRequest request) {
        //检查是否已经登陆
        User user = loginRegisterService.checkLoginStatus(request.getCookies());
        if (user == null) {
            model.addAttribute("user", new User());
            model.addAttribute("isSignin", false);
            return "signin";
        } else {
            model.addAttribute("isSignin", true);
            model.addAttribute("user", user);

            //拉出用户UID对应的地址,放在result表中
            List<UserAddress> result = myInfoService.myaddress(user.getUid());
            if (result.isEmpty()) {//如果result表为空,则不作显示
                model.addAttribute("isEmpty", true);
            } else {
                model.addAttribute("isEmpty", false);
                model.addAttribute("addressList", result);
                model.addAttribute("editAddress", new UserAddress());
            }
            return "myinfo";
        }
    }

    @RequestMapping("/address_edit")
    public String edit(Model model, HttpServletRequest request, HttpServletResponse response, @RequestParam("id") String id, @RequestParam("province") String province, @RequestParam("city") String city, @RequestParam("detailAddress") String detailAddress) {
        //检查是否已经登陆
        System.out.println("asdasd" + id);


        User user = loginRegisterService.checkLoginStatus(request.getCookies());
        if (user == null) {
            model.addAttribute("user", new User());
            model.addAttribute("isSignin", false);
            return "signin";
        } else {
            model.addAttribute("isSignin", true);
            model.addAttribute("user", user);


            UserAddress userAddress = new UserAddress();
            userAddress.setId(Integer.valueOf(id));
            userAddress.setProvince(province);
            userAddress.setCity(city);
            userAddress.setDetailAddress(detailAddress);
            Boolean result = myInfoService.edit(userAddress);

            model.addAttribute("ifsuccess", result);
            model.addAttribute("msg", "提交成功");
            return "myinfo";
        }
    }

    @RequestMapping("/myorder")
    public String myOrder(Model model, HttpServletRequest request, @RequestParam(value = "page", required = false) Integer page) {
        //检查是否已经登陆
        User user = loginRegisterService.checkLoginStatus(request.getCookies());
        if (user == null) {
            model.addAttribute("user", new User());
            model.addAttribute("isSignin", false);
            return "signin";

        } else {
            model.addAttribute("user", user);
            model.addAttribute("isSignin", true);

            //获取请求页数
            //设置每页显示数量为5
            int pageSize = 5;
            if (page == null) page = 1;//若未接受到页面请求，则设置为1
            //生成页面列表
            List<Integer> pagesNumberList = new ArrayList<>();
            long count = myInfoService.getUserOrderCount(user.getUid());
            for (int i = 1; i <= (count % pageSize == 0 ? count / pageSize : (count / pageSize + 1)); i++) {
                pagesNumberList.add(i);
            }
            List<OrderPageModel> orderPageModels = myInfoService.getOrderPageInfo(user.getUid(), pageSize, page);
            model.addAttribute("pageNumberList", pagesNumberList);
            model.addAttribute("currentPage", page);
            model.addAttribute("pageAmount", pagesNumberList.size());
            model.addAttribute("orderPageList", orderPageModels);
            return "myorder";
        }
    }

    @RequestMapping("/myhistory")
    public String myhistory(Model model, HttpServletRequest request) {
        //检查是否已经登陆
        User user = loginRegisterService.checkLoginStatus(request.getCookies());
        if (user == null) {
            model.addAttribute("user", new User());
            model.addAttribute("isSignin", false);
            return "signin";
        } else {
            model.addAttribute("isSignin", true);
            model.addAttribute("user", user);

            //1从页面拿到查看天数days,然后拉取user_history表找对应uid且date.now-create_date<days的数据
            //2用得到的goods_uid去goods表中拉取数据。
            List<HistroyGoodsModel> historygoodslist = myInfoService.getHistoryGoods(user.getUid());
            if (historygoodslist.isEmpty()) {
                model.addAttribute("isEmpty", true);
            } else {

                model.addAttribute("isEmpty", false);

                model.addAttribute("list", historygoodslist);
            }
            return "myhistory";
        }

    }
}