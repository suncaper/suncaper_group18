package org.group18.back.Service.Impl;

import org.group18.back.Dao.*;
import org.group18.back.Entity.*;
import org.group18.back.Model.GoodsManagementModel;
import org.group18.back.Service.GoodsService;
import org.group18.back.Service.MystoreService;
import org.group18.back.Service.SellerService;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    SellerService sellerService;

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
    public Map<String, String> addGoods(GoodsManagementModel goodsManagementModel, String seller_uid, MultipartFile file, MultipartFile detail_img_file) {
        Map<String, String> result = new HashMap<>();
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria();
        criteria.andSellerUidEqualTo(seller_uid);
        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
        //相同商店goods_name不能重复
        Integer i;
        for(i = 0; i < goodsList.size(); i++){
            if (goodsList.get(i).getGoodsName().equals(goodsManagementModel.getGoods_name())){//goods_name相同
                GoodsSpecificationExample goodsSpecificationExample = new GoodsSpecificationExample();
                GoodsSpecificationExample.Criteria criteria1 = goodsSpecificationExample.createCriteria();
                criteria1.andGoodsUidEqualTo(goodsList.get(i).getUid());
                List<GoodsSpecification> goodsSpecificationList = goodsSpecificationMapper.selectByExample(goodsSpecificationExample);
                //某一商品的specification_name不能重复
                Integer j;
                for (j = 0; j < goodsSpecificationList.size(); j++){
                    if(goodsSpecificationList.get(j).getSpecificationName().equals(goodsManagementModel.getSpecification_name())){//specification_name相同
                        result.put("msg","该商品已存在，且规格名称不能重复");
                        return result;
                    }
                }
                if (j == goodsSpecificationList.size()){//规格不重复
                    //更新goods表
                    goodsList.get(i).setUid(goodsList.get(i).getUid());
                    goodsList.get(i).setAmount(goodsList.get(i).getAmount() + goodsManagementModel.getAmount());
                    goodsMapper.updateByPrimaryKey(goodsList.get(i));
                    //插入t_goods_specification
                    GoodsSpecification goodsSpecification = new GoodsSpecification();
                    goodsSpecification.setSpecificationName(goodsManagementModel.getSpecification_name());
                    goodsSpecification.setAmount(goodsManagementModel.getAmount());
                    goodsSpecification.setSaleAmount(0);
                    goodsSpecification.setGoodsUid(goodsList.get(i).getUid());
                    goodsSpecification.setDeleteState(false);
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
            //判断唯一性
            CategoryShopExample categoryShopExample = new CategoryShopExample();
            CategoryShopExample.Criteria criteria2 = categoryShopExample.createCriteria();
            criteria2.andCategoryUidEqualTo(goodsManagementModel.getCategory_uid());
            criteria2.andShopUidEqualTo(shops.get(0).getUid());
            List<CategoryShop> categoryShops = categoryShopMapper.selectByExample(categoryShopExample);
            if(categoryShops.isEmpty()){
                categoryShop.setShopUid(shops.get(0).getUid());
                categoryShopMapper.insert(categoryShop);
            }
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
            if(goodsManagementModel.getPoints() == null)
                goods.setPoints(0);
            else
                goods.setPoints(goodsManagementModel.getPoints());
            System.out.println(goodsManagementModel.getPoints());
            goods.setDeleteState(false);
            try {
                goods.setImgUrl(sellerService.fileupload(file));
            } catch (IOException e) {
                e.printStackTrace();
                goods.setImgUrl(goodsManagementModel.getImg_url());
            }
            try {
                goods.setDetailImgUrl(sellerService.fileupload(detail_img_file));
            } catch (IOException e) {
                e.printStackTrace();
                goods.setDetailImgUrl(goodsManagementModel.getDetail_img_url());
            }
            goodsMapper.insert(goods);
            //插入t_goods_specification
            GoodsSpecification goodsSpecification = new GoodsSpecification();
            goodsSpecification.setSpecificationName(goodsManagementModel.getSpecification_name());
            goodsSpecification.setAmount(goodsManagementModel.getAmount());
            goodsSpecification.setSaleAmount(0);
                //通过goods_name和seller_uid找到goods_uid
            GoodsExample goodsExample1 = new GoodsExample();
            GoodsExample.Criteria criteria3 = goodsExample1.createCriteria();
            criteria3.andSellerUidEqualTo(seller_uid);
            criteria3.andGoodsNameEqualTo(goodsManagementModel.getGoods_name());
            List<Goods> goodsList1 = goodsMapper.selectByExample(goodsExample1);
            goodsSpecification.setGoodsUid(goodsList1.get(0).getUid());
            goodsSpecification.setDeleteState(false);
            goodsSpecificationMapper.insert(goodsSpecification);
        }
        result.put("msg","商品添加成功");
        return result;
    }

    @Override
    public Integer getSellerOrderCount(String sellerUid) {
        OrderExample orderExample = new OrderExample();
        orderExample.or().andSellerUidEqualTo(sellerUid).andSellerDeleteStateEqualTo(false);
        return orderMapper.selectByExample(orderExample).size();
    }

    @Override

    public Shop getMyStoreInfo(String uid){
        ShopExample shopExample = new ShopExample();
        ShopExample.Criteria criteria = shopExample.createCriteria();
        criteria.andSellerUidEqualTo(uid);
        List<Shop> result = shopMapper.selectByExample(shopExample);

        Shop shop = result.get(0);
        return shop;
    }


    public void deleteSellerOrder(Integer orderId) {
        OrderExample orderExample = new OrderExample();
        orderExample.or().andIdEqualTo(orderId);
        Order order = orderMapper.selectByExample(orderExample).get(0);
        order.setSellerDeleteState(true);
        orderMapper.updateByExampleSelective(order, orderExample);
    }

    @Override
    public void agreeRefundOrder(Integer orderId) {
        OrderExample orderExample = new OrderExample();
        orderExample.or().andIdEqualTo(orderId);
        Order order = orderMapper.selectByExample(orderExample).get(0);
        order.setStateUid("state05");
        orderMapper.updateByExampleSelective(order, orderExample);
    }

    @Override
    public void refuseRefundOrder(Integer orderId) {
        OrderExample orderExample = new OrderExample();
        orderExample.or().andIdEqualTo(orderId);
        Order order = orderMapper.selectByExample(orderExample).get(0);
        order.setStateUid("state06");
        orderMapper.updateByExampleSelective(order, orderExample);
    }

    @Override
    public void mailGoods(Integer orderId) {
        OrderExample orderExample = new OrderExample();
        orderExample.or().andIdEqualTo(orderId);
        Order order = orderMapper.selectByExample(orderExample).get(0);
        order.setStateUid("state02");
        orderMapper.updateByExampleSelective(order, orderExample);
    }

    public boolean deleteGoods(Goods goods) {
        goods.setUid(goods.getUid());
        goods.setDeleteState(true);
        Integer result = goodsMapper.updateByPrimaryKey(goods);
        if (result > 0)
            return true;
        return false;
    }

    @Override
    public GoodsManagementModel getGoodsDetails(Goods goods) {
        GoodsManagementModel goodsManagementModel = new GoodsManagementModel();
        goodsManagementModel.setCategory_uid(goods.getCategoryUid());
        //查找种类描述
        CategoryExample categoryExample = new CategoryExample();
        CategoryExample.Criteria criteria = categoryExample.createCriteria();
        criteria.andUidEqualTo(goods.getCategoryUid());
        List<Category> categoryList = categoryMapper.selectByExample(categoryExample);
        goodsManagementModel.setCategory_description(categoryList.get(0).getDescriptions());

        goodsManagementModel.setGoods_name(goods.getGoodsName());
        goodsManagementModel.setPrice(goods.getPrice());
        goodsManagementModel.setDiscount_price(goods.getDiscountPrice());
        goodsManagementModel.setDescriptions(goods.getDescriptions());
        goodsManagementModel.setIs_exchange(goods.getIsExchange());
        goodsManagementModel.setPoints(goods.getPoints());
        //查找商品对应规格列表
        GoodsSpecificationExample goodsSpecificationExample = new GoodsSpecificationExample();
        GoodsSpecificationExample.Criteria criteria1 = goodsSpecificationExample.createCriteria();
        criteria1.andGoodsUidEqualTo(goods.getUid());
        List<GoodsSpecification> goodsSpecificationList = goodsSpecificationMapper.selectByExample(goodsSpecificationExample);
        goodsManagementModel.setGoodsSpecifications(goodsSpecificationList);
        return goodsManagementModel;
    }

    @Override
    public void editGoods(GoodsManagementModel goodsManagementModel, Integer goods_uid, MultipartFile file, MultipartFile detail_img_file) {
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria();
        criteria.andUidEqualTo(goods_uid);
        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
        goodsList.get(0).setUid(goods_uid);
        goodsList.get(0).setPrice(goodsManagementModel.getPrice());
        goodsList.get(0).setDiscountPrice(goodsManagementModel.getDiscount_price());
        goodsList.get(0).setGoodsName(goodsManagementModel.getGoods_name());
        goodsList.get(0).setDescriptions(goodsManagementModel.getDescriptions());
        if(!file.isEmpty()){
            try {
                goodsList.get(0).setImgUrl(sellerService.fileupload(file));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //找到detial_img_url
        if(!detail_img_file.isEmpty()){
            try {
                goodsList.get(0).setDetailImgUrl(goodsList.get(0).getDetailImgUrl() + "," + sellerService.fileupload(detail_img_file));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        goodsMapper.updateByPrimaryKey(goodsList.get(0));
    }


    @Override
    public List<GoodsSpecification> getGoodsSpecification(Integer goods_uid) {
//        Map<GoodsSpecification, Integer> goodsSpecificationMap = new HashMap<>();
        GoodsSpecificationExample goodsSpecificationExample = new GoodsSpecificationExample();
        GoodsSpecificationExample.Criteria criteria = goodsSpecificationExample.createCriteria();
        criteria.andGoodsUidEqualTo(goods_uid);
        criteria.andDeleteStateEqualTo(false);
        List<GoodsSpecification> goodsSpecificationList = goodsSpecificationMapper.selectByExample(goodsSpecificationExample);
        return goodsSpecificationList;
    }

    @Override
    public void editSpecification(GoodsSpecification goodsSpecification) {
        //更新t_goods_specification
        goodsSpecification.setUid(goodsSpecification.getUid());
        goodsSpecification.setSpecificationName(goodsSpecification.getSpecificationName());
        goodsSpecification.setAmount(goodsSpecification.getAmount());
        goodsSpecificationMapper.updateByPrimaryKey(goodsSpecification);
        //更新t_goods
        GoodsSpecificationExample goodsSpecificationExample = new GoodsSpecificationExample();
        GoodsSpecificationExample.Criteria criteria = goodsSpecificationExample.createCriteria();
        criteria.andGoodsUidEqualTo(goodsSpecification.getGoodsUid());
        criteria.andDeleteStateEqualTo(false);
        List<GoodsSpecification> goodsSpecificationList = goodsSpecificationMapper.selectByExample(goodsSpecificationExample);
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria1 = goodsExample.createCriteria();
        criteria1.andUidEqualTo(goodsSpecification.getGoodsUid());
        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
        goodsList.get(0).setUid(goodsList.get(0).getUid());
        Integer totalamount = 0;
        for(Integer i = 0; i < goodsSpecificationList.size(); i++){
            totalamount = totalamount + goodsSpecificationList.get(i).getAmount();
        }
        goodsList.get(0).setAmount(totalamount);
        goodsMapper.updateByPrimaryKey(goodsList.get(0));
    }

    @Override
    public void deleteSpecification(GoodsSpecification goodsSpecification) {
        //更新t_goods_specification
        goodsSpecification.setUid(goodsSpecification.getUid());
        goodsSpecification.setDeleteState(true);
        goodsSpecificationMapper.updateByPrimaryKey(goodsSpecification);
        //更新t_goods
        GoodsSpecificationExample goodsSpecificationExample = new GoodsSpecificationExample();
        GoodsSpecificationExample.Criteria criteria = goodsSpecificationExample.createCriteria();
        criteria.andGoodsUidEqualTo(goodsSpecification.getGoodsUid());
        criteria.andDeleteStateEqualTo(false);
        List<GoodsSpecification> goodsSpecificationList = goodsSpecificationMapper.selectByExample(goodsSpecificationExample);
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria1 = goodsExample.createCriteria();
        criteria1.andUidEqualTo(goodsSpecification.getGoodsUid());
        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
        if (!goodsSpecificationList.isEmpty()){
            goodsList.get(0).setUid(goodsList.get(0).getUid());
            Integer totalamount = 0;
            for(Integer i = 0; i < goodsSpecificationList.size(); i++){
                totalamount = totalamount + goodsSpecificationList.get(i).getAmount();
            }
            goodsList.get(0).setAmount(totalamount);
            goodsMapper.updateByPrimaryKey(goodsList.get(0));
        }else{
            goodsList.get(0).setUid(goodsList.get(0).getUid());
            goodsList.get(0).setDeleteState(true);
            goodsMapper.updateByPrimaryKey(goodsList.get(0));
        }
    }

    @Override
    public GoodsSpecification getSpecification(Integer specification_uid) {
        GoodsSpecificationExample goodsSpecificationExample = new GoodsSpecificationExample();
        GoodsSpecificationExample.Criteria criteria = goodsSpecificationExample.createCriteria();
        criteria.andUidEqualTo(specification_uid);
        List<GoodsSpecification> goodsSpecificationList = goodsSpecificationMapper.selectByExample(goodsSpecificationExample);
        return goodsSpecificationList.get(0);
    }

    @Override
    public void addSpecification(Integer goods_uid, GoodsSpecification newgoodsSpecification) {
        GoodsSpecificationExample goodsSpecificationExample = new GoodsSpecificationExample();
        GoodsSpecificationExample.Criteria criteria = goodsSpecificationExample.createCriteria();
        criteria.andGoodsUidEqualTo(goods_uid);
        List<GoodsSpecification> goodsSpecificationList = goodsSpecificationMapper.selectByExample(goodsSpecificationExample);
        Integer i;
        for(i = 0; i < goodsSpecificationList.size(); i++){
            if (goodsSpecificationList.get(i).getSpecificationName().equals(newgoodsSpecification.getSpecificationName())){
                if(goodsSpecificationList.get(i).getDeleteState()){
                    goodsSpecificationList.get(i).setUid(goodsSpecificationList.get(i).getUid());
                    goodsSpecificationList.get(i).setAmount(newgoodsSpecification.getAmount());
                    goodsSpecificationList.get(i).setDeleteState(false);
                    goodsSpecificationMapper.updateByPrimaryKey(goodsSpecificationList.get(i));
                    break;
                }
                return;
            }
        }
        if (i == goodsSpecificationList.size()){
            newgoodsSpecification.setSaleAmount(0);
            newgoodsSpecification.setGoodsUid(goods_uid);
            newgoodsSpecification.setDeleteState(false);
            goodsSpecificationMapper.insert(newgoodsSpecification);
        }
        //更新t_goods
        GoodsSpecificationExample goodsSpecificationExample1 = new GoodsSpecificationExample();
        GoodsSpecificationExample.Criteria criteria1 = goodsSpecificationExample1.createCriteria();
        criteria1.andGoodsUidEqualTo(newgoodsSpecification.getGoodsUid());
        criteria1.andDeleteStateEqualTo(false);
        List<GoodsSpecification> newgoodsSpecificationList = goodsSpecificationMapper.selectByExample(goodsSpecificationExample1);
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria2 = goodsExample.createCriteria();
        criteria2.andUidEqualTo(newgoodsSpecification.getGoodsUid());
        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
        if (!goodsSpecificationList.isEmpty()){
            goodsList.get(0).setUid(goodsList.get(0).getUid());
            Integer totalamount = 0;
            for(Integer j = 0; j < newgoodsSpecificationList.size(); j++){
                totalamount = totalamount + newgoodsSpecificationList.get(j).getAmount();
            }
            goodsList.get(0).setAmount(totalamount);
            goodsMapper.updateByPrimaryKey(goodsList.get(0));
        }else{
            goodsList.get(0).setUid(goodsList.get(0).getUid());
            goodsList.get(0).setDeleteState(true);
            goodsMapper.updateByPrimaryKey(goodsList.get(0));
        }
    }
}
