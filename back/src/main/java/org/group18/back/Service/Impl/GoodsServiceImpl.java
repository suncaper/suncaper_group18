package org.group18.back.Service.Impl;

import org.group18.back.Dao.GoodsMapper;
import org.group18.back.Dao.TicketMapper;
import org.group18.back.Dao.UserMapper;
import org.group18.back.Entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Autowired
    UserMapper userMapper;

    @Autowired
    TicketMapper ticketMapper;

    @Override
    public Goods getReview(int goods_uid){
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria();
        criteria.andUidEqualTo(goods_uid);
        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
        Goods goods = goodsList.get(0);
        return goods;
    }

    @Override
    public List<Goods> getReviews(int goods_uid) {
        GoodsExample goodsExample = new GoodsExample();
        GoodsExample.Criteria criteria = goodsExample.createCriteria();
        criteria.andUidEqualTo(goods_uid);
        List<Goods> goodsList = goodsMapper.selectByExample(goodsExample);
        return goodsList;
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
