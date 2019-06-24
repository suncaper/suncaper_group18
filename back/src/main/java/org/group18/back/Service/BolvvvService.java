package org.group18.back.Service;

import org.group18.back.Entity.Shop;
import org.group18.back.Model.ShopPageModel;

import java.util.List;

public interface BolvvvService {
    ShopPageModel getShopPageModel(Integer shopUid);
    List<Shop> getShopList(int pageSize, int page);
    Integer getAllShopCount();
}
