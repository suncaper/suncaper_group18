package org.group18.back.Service;

import org.group18.back.Entity.Shop;
import org.group18.back.Model.ShopPageModel;

import java.util.List;

public interface StoreService {
    ShopPageModel getShopPageModel(Integer shopUid,int pageSize, int page);
    List<Shop> getShopList(int pageSize, int page);
    Integer getAllShopCount();
}
