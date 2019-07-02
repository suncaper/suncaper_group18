package org.group18.back.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {

    @RequestMapping("/search")
    public String search(@RequestParam(value = "searchKey", required = true) String searchKey, @RequestParam(value = "searchCategory", required = true) String searchCategory){
        //searchGoods包含：stores和goods
        if(searchCategory.equals("stores")){
            return "redirect:/getStoreList?searchKey="+searchKey;
        }
        else {
            return "redirect:/getGoodsList?searchKey="+searchKey;
        }
    }
}
