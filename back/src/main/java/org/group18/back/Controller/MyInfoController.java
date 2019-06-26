package org.group18.back.Controller;


import org.apache.ibatis.annotations.Param;
import org.group18.back.Dao.UserAddressMapper;
import org.group18.back.Entity.User;
import org.group18.back.Entity.UserAddress;
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

            //拉出用户UID对应的地址,放在result表中
            List<UserAddress> result = myInfoService.myaddress(user.getUid());
            if(result.isEmpty())
            {//如果result表为空,则不作显示
                model.addAttribute("isEmpty",true);
            }
            else
            {
                model.addAttribute("isEmpty",false);
                model.addAttribute("address", result);
            }
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

}
