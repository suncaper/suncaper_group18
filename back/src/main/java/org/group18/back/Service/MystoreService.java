package org.group18.back.Service;

import org.group18.back.Entity.Category;
import org.group18.back.Entity.Goods;
import org.group18.back.Entity.GoodsSpecification;
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
    void deleteSellerOrder(Integer orderId);
    void agreeRefundOrder(Integer orderId);
    void refuseRefundOrder(Integer orderId);
    void mailGoods(Integer orderId);
    boolean deleteGoods(Goods goods);
    GoodsManagementModel getGoodsDetails(Goods goods);
    void editGoods(GoodsManagementModel goodsManagementModel, Integer goods_uid, MultipartFile file, MultipartFile detail_img_file);
    List<GoodsSpecification> getGoodsSpecification(Integer goods_uid);
    void editSpecification(GoodsSpecification goodsSpecification);
    void deleteSpecification(GoodsSpecification goodsSpecification);
    GoodsSpecification getSpecification(Integer specification_uid);
    void addSpecification(Integer goods_uid, GoodsSpecification newgoodsSpecification);
}
