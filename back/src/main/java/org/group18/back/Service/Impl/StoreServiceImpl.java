package org.group18.back.Service.Impl;

import org.group18.back.Dao.CategoryMapper;
import org.group18.back.Dao.GoodsMapper;
import org.group18.back.Dao.ShopMapper;
import org.group18.back.Entity.*;
import org.group18.back.Model.ShopPageModel;
import org.group18.back.Service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {
    @Autowired
    ShopMapper shopMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public ShopPageModel getShopPageModel(Integer shopUid,int pageSize, int page) {
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
            goodsExample.setPageSize(pageSize);
            goodsExample.setStartRow((page-1)*pageSize);
            List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
            shopPageModel.setShopGoodsList(goodsList);

            //添加本店商品销售数量
            int salesVolumeCount = 0;
            for(Goods goods : goodsList){
                salesVolumeCount = salesVolumeCount+goods.getSalesVolume();
            }
            shopPageModel.setSalesVolumeCount(salesVolumeCount);
            return shopPageModel;
        }
    }

    @Override
    public List<Shop> getShopList(int pageSize, int page, String searchKey) {
        ShopExample shopExample = new ShopExample();
        if(searchKey != null) shopExample.or().andShopNameLike("%"+searchKey+"%");//如果搜索关键词不为空，则添加搜索关键词
        shopExample.setStartRow((page-1)*pageSize);
        shopExample.setPageSize(pageSize);
        return shopMapper.selectByExample(shopExample);
    }

    @Override
    public Integer getAllShopCount(String searchKey) {
        ShopExample shopExample = new ShopExample();
        if(searchKey != null) shopExample.or().andShopNameLike("%"+searchKey+"%");//如果搜索关键词不为空，则添加搜索关键词
        return shopMapper.selectByExample(shopExample).size();
    }


    @Override
    public List<Shop> selectAllShop(){
       List<Shop> getAllShop = shopMapper.selectAllShop();
       return getAllShop;
    }
}
