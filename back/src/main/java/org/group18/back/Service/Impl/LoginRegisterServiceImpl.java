package org.group18.back.Service.Impl;

import org.group18.back.Dao.TicketMapper;
import org.group18.back.Dao.UserMapper;
import org.group18.back.Entity.Ticket;
import org.group18.back.Entity.TicketExample;
import org.group18.back.Entity.User;
import org.group18.back.Entity.UserExample;
import org.group18.back.Service.LoginRegisterService;
import org.group18.back.Utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.*;

@Service
public class LoginRegisterServiceImpl implements LoginRegisterService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    TicketMapper ticketMapper;

    @Override
    @Transactional//添加事事务注解，当发生异常情况时回退
    public Map<String, String> register(String userName, String password, String email) {
        Map<String, String> result = new HashMap<>();
        if (userName.equals("") || password.equals("") || email.equals("")){
            result.put("msg","输入字段不能为空");
            return result;
        }

        UserExample userExample1 = new UserExample();
        UserExample.Criteria criteria1 = userExample1.createCriteria();
        criteria1.andUserNameEqualTo(userName);
        List<User> userList1 = userMapper.selectByExample(userExample1);
        if(!userList1.isEmpty()){
            result.put("msg", "用户名已注册");
            return result;
        }

        UserExample userExample2 = new UserExample();
        UserExample.Criteria criteria2 = userExample2.createCriteria();
        criteria2.andEmailEqualTo(email);
        List<User> userList2 = userMapper.selectByExample(userExample2);
        if(!userList2.isEmpty()){
            result.put("msg", "邮箱已注册");
            return result;
        }

        //注册新用户-
        User user = new User();
        user.setUserName(userName);
        user.setEmail(email);
        user.setPassWord(MD5Utils.getMD5(password));
        user.setUid(getUserUid());
        user.setRegistrationDate(new Date(System.currentTimeMillis()));
        user.setBalance(BigDecimal.valueOf(0.00));
        user.setIsSeller(false);
        user.setPoints(0);
        user.setPhone(null);
        userMapper.insert(user);
        //添加ticket进数据库
        String ticket = addLoginTicket(user.getEmail());
        result.put("ticket", ticket);

        return result;
    }

    @Override
    public Map<String, String> login(String email, String password) {
        Map<String,String> result = new HashMap<>();
        if (password.equals("") || email.equals("")){
            result.put("msg","输入字段不能为空");
            return result;
        }

        UserExample userExample = new UserExample();
        userExample.createCriteria().andEmailEqualTo(email);
        List<User> userList = userMapper.selectByExample(userExample);

        if(userList.isEmpty()) {
            result.put("msg", "邮箱不存在");
            return result;
        }

        if(!userList.get(0).getPassWord().equals(MD5Utils.getMD5(password))){
            result.put("msg", "密码错误");
            return result;
        }

        String ticket = addLoginTicket(email);
        result.put("ticket", ticket);
        return result;
    }

    @Override
    public User getUserInfoByTicket(String ticket) {
        TicketExample ticketExample = new TicketExample();
        ticketExample.createCriteria().andTicketEqualTo(ticket).andExpireDateGreaterThan(new Date(System.currentTimeMillis()));
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

    @Override
    public User checkLoginStatus(Cookie[] cookies) {
        //有ticket并且能在数据库中查询到对应用户信息则返回信息，否则返回null
        if(cookies != null){
            for(Cookie cookie : cookies){
                if(cookie.getName().equals("ticket")){
                    if(cookie.getValue() == null||cookie.getValue().isEmpty()) return null;
                    //此时证明用户已有本网站的ticket，因此查询数据库获取用户信息
                    User user = getUserInfoByTicket(cookie.getValue());
                    if(user == null) {
                        return null;
                    }
                    else return user;
                }
            }
        }
        return null;
    }

    private String addLoginTicket(String userEmail){
        Ticket ticket = new Ticket();
        ticket.setUserEmail(userEmail);
        ticket.setIsTimeOut(false);
        ticket.setExpireDate(new Date(System.currentTimeMillis()+2*3600*1000));//设定ticket过期时间为2h
        ticket.setTicket(UUID.randomUUID().toString().replaceAll("-",""));
        ticketMapper.insert(ticket);
        return ticket.getTicket();
    }

    private String getUserUid(){
        return String.valueOf(System.currentTimeMillis());
    }
}
