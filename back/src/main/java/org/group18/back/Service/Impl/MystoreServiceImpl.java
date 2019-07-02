package org.group18.back.Service.Impl;

import org.group18.back.Dao.*;
import org.group18.back.Entity.*;
import org.group18.back.Model.GoodsManagementModel;
import org.group18.back.Service.MystoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MystoreServiceImpl implements MystoreService{
    @Autowired
    ShopMapper shopMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    GoodsMapper goodsMapper;
    @Autowired
    CategoryShopMapper categoryShopMapper;
    @Autowired
    GoodsSpecificationMapper goodsSpecificationMapper;

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

    @Override
    public Map<String, String> addGoods(GoodsManagementModel goodsManagementModel, String seller_uid) {
        Map<String, String> result = new HashMap<>();
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria();
        criteria.andSellerUidEqualTo(seller_uid);
        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
        //相同商店goods_name不能重复
        Integer i;
        for(i = 0; i < goodsList.size(); i++){
            if (goodsList.get(i).getGoodsName() == goodsManagementModel.getGoods_name()){//goods_name相同
                GoodsSpecificationExample goodsSpecificationExample = new GoodsSpecificationExample();
                GoodsSpecificationExample.Criteria criteria1 = goodsSpecificationExample.createCriteria();
                criteria1.andGoodsUidEqualTo(goodsList.get(i).getUid());
                List<GoodsSpecification> goodsSpecificationList = goodsSpecificationMapper.selectByExample(goodsSpecificationExample);
                //某一商品的specification_name不能重复
                Integer j;
                for (j = 0; j < goodsSpecificationList.size(); j++){
                    if(goodsSpecificationList.get(j).getSpecificationName() == goodsManagementModel.getSpecification_name()){//specification_name相同
                        result.put("msg","该商品已存在，且规格名称不能重复");
                        return result;
                    }
                }
                if (j == goodsSpecificationList.size()){
                    //更新goods表
                    goodsList.get(i).setUid(goodsList.get(i).getUid());
                    goodsList.get(i).setAmount(goodsList.get(i).getAmount() + goodsManagementModel.getAmount());
                    //插入t_goods_specification
                    GoodsSpecification goodsSpecification = new GoodsSpecification();
                    goodsSpecification.setSpecificationName(goodsManagementModel.getSpecification_name());
                    goodsSpecification.setAmount(goodsManagementModel.getAmount());
                    goodsSpecification.setSaleAmount(0);
                    goodsSpecification.setGoodsUid(goodsList.get(i).getUid());
                    goodsSpecificationMapper.insert(goodsSpecification);
                    result.put("msg","规格添加成功");
                    return result;
                }
            }
        }
        if (i == goodsList.size()){
            //insert t_category_shop
            CategoryShop categoryShop = new CategoryShop();
            categoryShop.setCategoryUid(goodsManagementModel.getCategory_uid());
                //通过selleruid找到shopuid
            ShopExample shopExample = new ShopExample();
            ShopExample.Criteria criteria1 = shopExample.createCriteria();
            criteria1.andSellerUidEqualTo(seller_uid);
            List<Shop> shops = shopMapper.selectByExample(shopExample);
            categoryShop.setShopUid(shops.get(0).getUid());
            categoryShopMapper.insert(categoryShop);
            //insert t_goods
            Goods goods = new Goods();
            goods.setCategoryUid(goodsManagementModel.getCategory_uid());
            goods.setPrice(goodsManagementModel.getPrice());
            goods.setDiscountPrice(goodsManagementModel.getDiscount_price());
            goods.setGoodsName(goodsManagementModel.getGoods_name());
            goods.setAmount(goodsManagementModel.getAmount());
            goods.setSalesVolume(0);
            goods.setDescriptions(goodsManagementModel.getDescriptions());
            goods.setSellerUid(seller_uid);
            goods.setIsExchange(goodsManagementModel.getIs_exchange());
            goods.setPoints(goodsManagementModel.getPoints());
            goods.setImgUrl(goodsManagementModel.getImg_url());
            goods.setDetailImgUrl(goodsManagementModel.getDetail_img_url());
            goodsMapper.insert(goods);
            //插入t_goods_specification
            GoodsSpecification goodsSpecification = new GoodsSpecification();
            goodsSpecification.setSpecificationName(goodsManagementModel.getSpecification_name());
            goodsSpecification.setAmount(goodsManagementModel.getAmount());
            goodsSpecification.setSaleAmount(0);
                //通过goods_name和seller_uid找到goods_uid
            GoodsExample goodsExample1 = new GoodsExample();
            GoodsExample.Criteria criteria2 = goodsExample1.createCriteria();
            criteria2.andSellerUidEqualTo(seller_uid);
            criteria2.andGoodsNameEqualTo(goodsManagementModel.getGoods_name());
            List<Goods> goodsList1 = goodsMapper.selectByExample(goodsExample1);
            goodsSpecification.setGoodsUid(goodsList1.get(0).getUid());
            goodsSpecificationMapper.insert(goodsSpecification);
        }
        result.put("msg","商品添加成功");
        return result;
    }
}
