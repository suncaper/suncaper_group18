package org.group18.back.Controller;


import org.group18.back.Service.LoginRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginRegisterController {
    @Autowired
    LoginRegisterService loginRegisterService;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/register")
    public void register(@RequestParam("userName") String userName,@RequestParam("password") String password,@RequestParam("email") String email){
        loginRegisterService.register(userName, password, email);
    }
}
