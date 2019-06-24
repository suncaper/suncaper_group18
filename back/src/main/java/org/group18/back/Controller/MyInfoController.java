package org.group18.back.Controller;


import org.group18.back.Entity.User;
import org.group18.back.Service.LoginRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class MyInfoController {
//    @Autowired
//    MyInfoService
    @RequestMapping("/myinfo")
    public String wishlist()
    {
        return "myinfo";
    }

}
