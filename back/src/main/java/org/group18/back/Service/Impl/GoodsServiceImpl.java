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

    @Autowired
    CartMapper cartMapper;



    @Override
    public GoodsDeatilInfoModel getGoods(int goods_uid) {
        GoodsDeatilInfoModel goodsDeatilInfoModel = new GoodsDeatilInfoModel();
        //查询商品
        GoodsExample goodsExample1 = new GoodsExample();
        goodsExample1.createCriteria().andUidEqualTo(goods_uid);
        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample1);
        if(goodsList == null) return null;
        Goods goods = goodsList.get(0);
        goodsDeatilInfoModel.setGoods(goods);
        //查询商品规格
        GoodsSpecificationExample goodsSpecificationExample = new GoodsSpecificationExample();
        goodsSpecificationExample.or().andGoodsUidEqualTo(goods.getUid());
        goodsDeatilInfoModel.setGoodsSpecification(goodsSpecificationMapper.selectByExample(goodsSpecificationExample));
        //查询店铺
        ShopExample shopExample = new ShopExample();
        shopExample.createCriteria().andSellerUidEqualTo(goods.getSellerUid());
        goodsDeatilInfoModel.setShop(shopMapper.selectByExample(shopExample).get(0));
        //查询评论
        GoodsReviewExample goodsReviewExample = new GoodsReviewExample();
        goodsReviewExample.or().andGoodsUidEqualTo(goods_uid);
        goodsDeatilInfoModel.setGoodsReviewList(goodsReviewMapper.selectByExample(goodsReviewExample));
        //查询推荐商品
        GoodsExample goodsExample2 = new GoodsExample();
        goodsExample2.or().andSellerUidEqualTo(goods.getSellerUid()).andCategoryUidEqualTo(goods.getCategoryUid());
        goodsExample2.setOrderByClause("sales_volume desc");
        goodsDeatilInfoModel.setRecommendGoods(goodsMapper.selectByExample(goodsExample2));
        //添加商品详细信息图片
        goodsDeatilInfoModel.setGoodsDetailImg(goods.getDetailImgUrl().split(","));
        return goodsDeatilInfoModel;
    }

    @Override

    public void addGoodsToCart(String userUid, Integer goodsUid, Integer specificationUid, Integer counts, String payWay) {
        //判断购物车中是否已经存在相应商品
        CartExample cartExample = new CartExample();
        //根据payWay是"points"还是"money"判断isExchange的值
        cartExample.or().andUserUidEqualTo(userUid).andGoodsUidEqualTo(goodsUid).andSpecificationUidEqualTo(specificationUid).andIsExchangeEqualTo(payWay.equals("points"));
        List<Cart> cartList = cartMapper.selectByExample(cartExample);
        if(cartList.isEmpty()){
            Cart cart = new Cart();
            cart.setAmount(counts);
            cart.setCreateDate(new Date(System.currentTimeMillis()));
            cart.setGoodsUid(goodsUid);
            cart.setSpecificationUid(specificationUid);
            cart.setUserUid(userUid);
            cart.setIsExchange(payWay.endsWith("points"));
            cartMapper.insert(cart);
        }
        else {
            cartList.get(0).setAmount(cartList.get(0).getAmount()+counts);
            cartMapper.updateByPrimaryKeySelective(cartList.get(0));
        }
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
