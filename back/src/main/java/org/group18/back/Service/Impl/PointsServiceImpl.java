package org.group18.back.Service.Impl;

import org.apache.ibatis.jdbc.Null;
import org.group18.back.Dao.GoodsMapper;
import org.group18.back.Dao.ShopMapper;
import org.group18.back.Entity.GoodsExample;
import org.group18.back.Entity.Goods;
import org.group18.back.Entity.ShopExample;
import org.group18.back.Entity.Shop;
import org.group18.back.Model.PointsShopModel;
import org.group18.back.Service.PointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PointsServiceImpl implements PointsService {
    @Autowired
    ShopMapper shopMapper;
    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public List<PointsShopModel> getShopGoods(){

        ShopExample shopExample = new ShopExample();
        ShopExample.Criteria criteria = shopExample.createCriteria();
        criteria.andUidIsNotNull();
        List<Shop> shopList = shopMapper.selectByExample(shopExample);


        List<PointsShopModel> pointsShopModelList = new ArrayList<>();

        for(int i=0;i<shopList.size();i++)
        {
            GoodsExample goodsExample = new GoodsExample();
            GoodsExample.Criteria criteria1 = goodsExample.createCriteria();
            criteria1.andSellerUidEqualTo(shopList.get(i).getSellerUid());
            criteria1.andIsExchangeEqualTo(true);
            List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);

            PointsShopModel pointsShopModel = new PointsShopModel();
            pointsShopModel.setShop(shopList.get(i));
            pointsShopModel.setGoodsList(goodsList);
            pointsShopModelList.add(pointsShopModel);
        }

        return pointsShopModelList;
    }
}
