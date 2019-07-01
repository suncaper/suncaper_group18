package org.group18.back.Service.Impl;

import org.group18.back.Dao.CartMapper;
import org.group18.back.Dao.CategoryMapper;
import org.group18.back.Dao.GoodsMapper;
import org.group18.back.Dao.ShopMapper;
import org.group18.back.Entity.*;
import org.group18.back.Service.MystoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MystoreServiceImpl implements MystoreService{
    @Autowired
    ShopMapper shopMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public Integer getShopUid(String user_uid){
        ShopExample shopExample = new ShopExample();
        ShopExample.Criteria criteria = shopExample.createCriteria();
        criteria.andSellerUidEqualTo(user_uid);
        List<Shop> shops = shopMapper.selectByExample(shopExample);
        return shops.get(0).getUid();
    }

    @Override
    public List<Category> getAllCategory(){
        CategoryExample categoryExample = new CategoryExample();
        CategoryExample.Criteria criteria = categoryExample.createCriteria();
        criteria.andUidIsNotNull();
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);
        return categoryList;
    }

    @Override
    public Goods getGoods(Integer goods_uid){
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria();
        criteria.andUidEqualTo(goods_uid);
        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
        return goodsList.get(0);
    }
}
