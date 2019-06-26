package org.group18.back.Service.Impl;

import org.group18.back.Dao.UserAddressMapper;
import org.group18.back.Dao.UserMapper;
import org.group18.back.Entity.User;
import org.group18.back.Entity.UserAddress;
import org.group18.back.Entity.UserAddressExample;
import org.group18.back.Entity.UserExample;
import org.group18.back.Service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CheckoutServiceImpl implements CheckoutService{
    @Autowired
    private UserAddressMapper userAddressMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<UserAddress> getUserAddress(String user_uid){
        UserAddressExample userAddressExample = new UserAddressExample();
        UserAddressExample.Criteria criteria = userAddressExample.createCriteria();
        criteria.andUserUidEqualTo(user_uid);
        List<UserAddress> userAddressList = userAddressMapper.selectByExample(userAddressExample);
        return userAddressList;
    }

    @Override
    public BigDecimal getUserBalance(String user_uid){
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUidEqualTo(user_uid);
        List<User> user = userMapper.selectByExample(userExample);
        return user.get(0).getBalance();
    }

    @Override
    public Integer getUserPoints(String user_uid){
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andUidEqualTo(user_uid);
        List<User> user = userMapper.selectByExample(userExample);
        return user.get(0).getPoints();
    }
}
