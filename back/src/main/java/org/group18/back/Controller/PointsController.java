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
public class PointsController {
    @Autowired
    LoginRegisterService loginRegisterService;
    @RequestMapping("/points")
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

            return "points";
        }
    }
}
