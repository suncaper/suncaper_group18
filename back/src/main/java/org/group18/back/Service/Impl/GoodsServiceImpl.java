package org.group18.back.Service.Impl;

import org.group18.back.Dao.*;
import org.group18.back.Entity.*;
import org.group18.back.Model.CartListModel;
import org.group18.back.Model.GoodsDeatilInfoModel;
import org.group18.back.Service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.*;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    TicketMapper ticketMapper;

    @Autowired
    ShopMapper shopMapper;

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    GoodsReviewMapper goodsReviewMapper;

    @Autowired
    GoodsSpecificationMapper goodsSpecificationMapper;

    @Override
    public Goods getGood(int goods_uid){
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria();
        criteria.andUidEqualTo(goods_uid);
        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
        Goods goods = goodsList.get(0);
        return goods;
    }

    @Override
    public GoodsDeatilInfoModel getGoods(int goods_uid) {

        GoodsDeatilInfoModel goodsDeatilInfoModel = new GoodsDeatilInfoModel();

        GoodsExample goodsExample1 = new GoodsExample();
        goodsExample1.createCriteria().andUidEqualTo(goods_uid);
        Goods goods = goodsMapper.selectByExample(goodsExample1).get(0);
        goodsDeatilInfoModel.setGoods(goods);


        ShopExample shopExample = new ShopExample();
        shopExample.createCriteria().andSellerUidEqualTo(goods.getSellerUid());
        goodsDeatilInfoModel.setShop(shopMapper.selectByExample(shopExample).get(0));



        GoodsReviewExample goodsReviewExample = new GoodsReviewExample();
        GoodsReviewExample.Criteria criteria = goodsReviewExample.createCriteria();
        criteria.andGoodsUidEqualTo(goods_uid);
       /* List <GoodsReview> goodsReview =goodsReviewMapper.selectByExample(goodsReviewExample);
        if ((goodsReview!=null))
        {
            goodsDeatilInfoModel.setGoodsReview(goodsReview.get(0));
        }*/

        CategoryExample categoryExample = new CategoryExample();
        categoryExample.createCriteria().andUidEqualTo(goods.getCategoryUid());
        Category category = categoryMapper.selectByExample(categoryExample).get(0);
        GoodsExample goodsExample2 = new GoodsExample();
        goodsExample2.createCriteria().andCategoryUidEqualTo(category.getUid()).andSellerUidEqualTo(goods.getSellerUid());
        goodsExample2.setOrderByClause("sales_volume desc");
        goodsDeatilInfoModel.setRecommendGoods(goodsMapper.selectByExample(goodsExample2));

        GoodsSpecificationExample goodsSpecificationExample = new GoodsSpecificationExample();
        goodsSpecificationExample.createCriteria().andUidEqualTo(goods_uid);
        goodsDeatilInfoModel.setGoodsSpecification(goodsSpecificationMapper.selectByExample(goodsSpecificationExample));


        return goodsDeatilInfoModel;
    }







    public User judgeUserLoginStatus(String ticket) {
        TicketExample ticketExample = new TicketExample();
        ticketExample.createCriteria().andTicketEqualTo(ticket).andExpireDateGreaterThan(new java.sql.Date(System.currentTimeMillis()));
        List<Ticket> tickets = ticketMapper.selectByExample(ticketExample);
        if(tickets.isEmpty()){
            return null;
        }
        else {
            UserExample userExample = new UserExample();
            userExample.createCriteria().andEmailEqualTo(tickets.get(0).getUserEmail());
            List<User> users = userMapper.selectByExample(userExample);
            return users.get(0);
        }
    }

    public User checkTicket(Cookie[] cookies) {
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("ticket")){
                    if(cookie.getValue() == null) return null;
                    //此时证明用户已有本网站的ticket，因此查询数据库获取用户信息
                    User user = judgeUserLoginStatus(cookie.getValue());
                    if(user == null) {
                        return null;
                    }
                    else return user;//TODO：需要添加前端页面修改逻辑
                }
            }
        }
        return null;
    }
}
