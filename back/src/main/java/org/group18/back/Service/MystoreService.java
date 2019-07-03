package org.group18.back.Service;

import org.group18.back.Entity.Category;
import org.group18.back.Entity.Goods;
import org.group18.back.Model.GoodsManagementModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface MystoreService {
    public Integer getShopUid(String user_uid);
    public List<Category> getAllCategory();
    public Goods getGoods(Integer goods_uid);
    public Map<String, String> addGoods(GoodsManagementModel goodsManagementModel, String seller_uid, MultipartFile file, MultipartFile detail_img_file);
    Integer getSellerOrderCount(String sellerUid);
}
