package org.group18.back.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.URLEncoder;

@Controller
public class SearchController {

    @RequestMapping("/search")
    public String search(@RequestParam(value = "searchKey", required = true) String searchKey, @RequestParam(value = "searchCategory", required = true) String searchCategory) throws Exception{
        //searchGoods包含：stores和goods
        if(searchCategory.equals("stores")){
            return "redirect:/getStoreList?searchKey="+ URLEncoder.encode(searchKey,"UTF-8");//防止中文编码出现错误
        }
        else {
            return "redirect:/getGoodsList?searchKey="+ URLEncoder.encode(searchKey,"UTF-8");
        }
    }
}
