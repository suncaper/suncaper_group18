package org.group18.back.Service;

import org.group18.back.Entity.Shop;
import org.group18.back.Entity.User;

import java.io.IOException;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

public interface SellerService {
    Map<String,String> newSellerShop(Shop shop,MultipartFile mfile);
    String fileupload(MultipartFile mfile) throws IOException;
}
