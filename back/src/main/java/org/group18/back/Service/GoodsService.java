package org.group18.back.Service;


import org.group18.back.Entity.Cart;
import org.group18.back.Entity.Goods;
import org.group18.back.Entity.GoodsSpecification;
import org.group18.back.Model.GoodsDeatilInfoModel;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface GoodsService {
    GoodsDeatilInfoModel getGoods(int goods_uid);
    void addGoodsToCart(String userUid, Integer goodsUid, Integer specificationUid, Integer counts, String payWay);
    Integer getGoodsSearchAmount(String searchKey);
    List<Goods> getGoodsSearchList(int pageSize, int page, String searchKey);
}
