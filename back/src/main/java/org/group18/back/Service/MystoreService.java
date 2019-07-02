package org.group18.back.Service;

import org.group18.back.Entity.Category;
import org.group18.back.Entity.Goods;

import java.util.List;

public interface MystoreService {
    public Integer getShopUid(String user_uid);
    public List<Category> getAllCategory();
    public Goods getGoods(Integer goods_uid);
}
