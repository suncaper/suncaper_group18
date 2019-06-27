package org.group18.back.Controller;


import org.apache.ibatis.annotations.Param;
import org.group18.back.Dao.UserAddressMapper;
import org.group18.back.Entity.User;
import org.group18.back.Entity.UserAddress;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class  MyInfoController {
    @Autowired
    MyInfoService myInfoService;
    @Autowired
    LoginRegisterService loginRegisterService;
    @RequestMapping("/myinfo")
    public String myinfo(Model model, HttpServletRequest request)
    {
        //检查是否已经登陆
        User user = loginRegisterService.checkLoginStatus(request.getCookies());
        if(user == null) {
            model.addAttribute("user", new User());
            model.addAttribute("isSignin", false);
            return "signin";
        }
        else {
            model.addAttribute("isSignin", true);
            model.addAttribute("user", user);
            List<UserAddress> userAddressList = myInfoService.getUserAddressList(user.getUid());
            if(userAddressList == null || userAddressList.isEmpty()){
                model.addAttribute("isAddressEmpty", true);
            }
            else {
                model.addAttribute("isAddressEmpty", false);
            }
            model.addAttribute("userAddressList", userAddressList);
            return "myinfo";
        }
    }
    @RequestMapping("/address_edit")

    public String edit(Model model, HttpServletRequest request,HttpServletResponse response, @RequestParam("id") String id, @RequestParam("province") String province, @RequestParam("city") String city, @RequestParam("detailAddress") String detailAddress){
        //检查是否已经登陆
        System.out.println("asdasd"+id);


        User user = loginRegisterService.checkLoginStatus(request.getCookies());
        if(user == null) {
            model.addAttribute("user", new User());
            model.addAttribute("isSignin", false);
            return "signin";
        }
        else{
            model.addAttribute("isSignin", true);
            model.addAttribute("user", user);


            UserAddress userAddress = new UserAddress();
            userAddress.setId(Integer.valueOf(id));
            userAddress.setProvince(province);
            userAddress.setCity(city);
            userAddress.setDetailAddress(detailAddress);
            Boolean result = myInfoService.edit(userAddress);

            model.addAttribute("ifsuccess",result);
            model.addAttribute("msg","提交成功");
            return"myinfo";
        }
    }
    @RequestMapping("/myorder")
    public String myOrder(Model model, HttpServletRequest request, @RequestParam(value = "page", required = false) Integer page){
        //检查是否已经登陆
        User user = loginRegisterService.checkLoginStatus(request.getCookies());
        if(user == null) {
            model.addAttribute("user", new User());
            model.addAttribute("isSignin", false);
            return "signin";

        }
        else {
            model.addAttribute("user", user);
            model.addAttribute("isSignin", true);
            //获取请求页数
            //设置每页显示数量为5
            int pageSize = 5;
            if(page == null) page = 1;//若未接受到页面请求，则设置为1
            //生成页面列表
            List<Integer> pagesNumberList = new ArrayList<>();
            long count = myInfoService.getUserOrderCount(user.getUid());
            for(int i = 1; i <= (count%pageSize==0?count/pageSize:(count/pageSize+1)); i++){
                pagesNumberList.add(i);
            }
            List<OrderPageModel> orderPageModels =  myInfoService.getOrderPageInfo(user.getUid(), pageSize, page);
            model.addAttribute("pageNumberList", pagesNumberList);
            model.addAttribute("currentPage", page);
            model.addAttribute("pageAmount", pagesNumberList.size());
            model.addAttribute("orderPageList", orderPageModels);
            return "myorder";
        }
    }

    @RequestMapping("/deleteMyOrder")
    public String deleteMyOrder(HttpServletRequest request, @RequestParam("orderId") Integer orderId){
        myInfoService.deleteUserOrder(orderId);
        return "redirect:/myorder";
    }

    @RequestMapping("/reviewGoods")
    public String reviewGoods(HttpServletRequest request, @RequestParam("goodsUid") Integer goodsUid, @RequestParam("specificationUid") Integer specificationUid,@RequestParam("review") String review, @RequestParam("payWay") String payWay){
        User user = loginRegisterService.checkLoginStatus(request.getCookies());
        myInfoService.reviewGoods(goodsUid,specificationUid, user.getUid(), payWay, review);
        return "redirect:/myorder";
    }

}
