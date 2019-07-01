package org.group18.back.Service;


import org.group18.back.Entity.Cart;
import org.group18.back.Entity.Goods;
import org.group18.back.Entity.GoodsSpecification;
import org.group18.back.Model.GoodsDeatilInfoModel;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface GoodsService {
    public GoodsDeatilInfoModel getGoods(int goods_uid);
    public void addCart(Cart cart, String user_uid, Integer goods_uid, Integer specification_uid, Integer counts);
    public List<Goods> selectAllGoodsByClausedesc();
}
