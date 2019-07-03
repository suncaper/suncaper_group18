package org.group18.back.Controller;

import org.group18.back.Entity.Goods;
import org.group18.back.Entity.User;
import org.group18.back.Service.CartService;
import org.group18.back.Service.CategoryService;
import org.group18.back.Service.LoginRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CategoryController {

        @Autowired
        LoginRegisterService loginRegisterService;

        @Autowired
        CartService cartService;

        @Autowired
        CategoryService categoryService;


        @RequestMapping("/Category")
        public String getCategoryGoods(Model model, HttpServletRequest request ){
            Integer category_uid = 1;
            User user = loginRegisterService.checkLoginStatus(request.getCookies());
            if(user == null) {
                model.addAttribute("user", new User());
                model.addAttribute("isSignin", false);
            }
            else {
                model.addAttribute("isSignin", true);
                model.addAttribute("user", user);

            }

            List<Goods> goodsList = categoryService.getCategoryGoods(category_uid);
            model.addAttribute("categoryGoods",goodsList);
            return "Category";
        }

}


