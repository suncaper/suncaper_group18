package org.group18.back.Service.Impl;

import org.group18.back.Dao.UserMapper;
import org.group18.back.Entity.*;
import org.group18.back.Service.SellerService;
import org.group18.back.Dao.GoodsMapper;
import org.group18.back.Dao.ShopMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SellerServiceImpl implements SellerService{

    @Autowired
    ShopMapper shopMapper;
    @Autowired
    UserMapper userMapper;


    @Override
    public Map<String,String> newSellerShop(Shop shop){//还差图片上传
        Map<String,String> result = new HashMap<>();
        if(shop.getShopName()==""||shop.getDescribes()==""||shop.getAddress()=="")
        {
            result.put("error","输入字段不能为空");
        }
        else{
            ShopExample shopExample1 = new ShopExample();
            ShopExample.Criteria criteria1 = shopExample1.createCriteria();
            criteria1.andShopNameEqualTo(shop.getShopName());
            List<Shop> shopList1 = shopMapper.selectByExample(shopExample1);
            if(!shopList1.isEmpty())
            {
                result.put("error","店铺名已被占用");
            }
            else
            {
                UserExample userExample1 = new UserExample();                   //将该user的isseller字段更新为真;
                UserExample.Criteria criteria2 = userExample1.createCriteria();
                criteria2.andUidEqualTo(shop.getSellerUid());
                User user = new User();
                user.setIsSeller(true);
                userMapper.updateByExampleSelective(user,userExample1);

                shopMapper.insert(shop);                               //插入数据
                result.put("success","");
            }
        }
        return result;
    }
}
