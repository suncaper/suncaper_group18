package org.group18.back.Model;

import org.group18.back.Entity.Shop;
import org.springframework.web.multipart.MultipartFile;

public class BecomeSellerModel {
    private Shop shop;
    private MultipartFile mfile;

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public MultipartFile getMfile() {
        return mfile;
    }

    public void setMfile(MultipartFile mfile) {
        this.mfile = mfile;
    }
}
