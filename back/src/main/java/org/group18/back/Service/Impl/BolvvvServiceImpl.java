package org.group18.back.Service.Impl;

import org.group18.back.Dao.CategoryMapper;
import org.group18.back.Dao.GoodsMapper;
import org.group18.back.Dao.ShopMapper;
import org.group18.back.Entity.*;
import org.group18.back.Model.ShopPageModel;
import org.group18.back.Service.BolvvvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BolvvvServiceImpl implements BolvvvService {
    @Autowired
    ShopMapper shopMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public ShopPageModel getShopPageModel(Integer shopUid) {
        ShopPageModel shopPageModel = new ShopPageModel();

        //查找店铺信息
        ShopExample shopExample = new ShopExample();
        shopExample.createCriteria().andUidEqualTo(shopUid);
        List<Shop> shopList = shopMapper.selectByExample(shopExample);
        if(shopList == null) return null;
        else {
            Shop shop = shopList.get(0);
            shopPageModel.setShopBaseInfo(shop);//设置店铺基本信息

            //查找与店铺对应的商品种类
            List<Category> categoryList = categoryMapper.findCategoryByShopUid(shopUid);
            shopPageModel.setShopCategories(categoryList);

            //查找本店商品
            GoodsExample goodsExample = new GoodsExample();
            goodsExample.createCriteria().andSellerUidEqualTo(shop.getSellerUid());
            List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
            shopPageModel.setShopGoodsList(goodsList);
            return shopPageModel;
        }
    }

    @Override
    public List<Shop> getShopList(int pageSize, int page) {
        ShopExample shopExample = new ShopExample();
        shopExample.setStartRow((page-1)*pageSize);
        shopExample.setPageSize(pageSize);
        return shopMapper.selectByExample(shopExample);
    }

    @Override
    public Integer getAllShopCount() {
        return shopMapper.getAllShopCount();
    }
}
