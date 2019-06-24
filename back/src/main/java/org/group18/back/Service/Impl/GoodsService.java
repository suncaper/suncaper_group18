package org.group18.back.Service.Impl;


import org.group18.back.Entity.Goods;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface GoodsService {
    public Goods getReview(int goods_uid);
    public List<Goods> getReviews(int goods_uid);
}
