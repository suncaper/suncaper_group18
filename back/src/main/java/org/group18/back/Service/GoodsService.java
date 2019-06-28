package org.group18.back.Service;


import org.group18.back.Entity.Goods;
import org.group18.back.Entity.GoodsSpecification;
import org.group18.back.Model.GoodsDeatilInfoModel;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface GoodsService {
    public Goods getGood(int goods_uid);
    public GoodsDeatilInfoModel getGoods(int goods_uid);
}
