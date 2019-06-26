package org.group18.back.Service.Impl;

import org.group18.back.Dao.TicketMapper;
import org.group18.back.Dao.UserAddressMapper;
import org.group18.back.Dao.UserMapper;
import org.group18.back.Entity.*;
import org.group18.back.Service.MyInfoService;
import org.group18.back.Utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.*;

@Service
public class MyInfoServiceImpl implements MyInfoService {

    @Autowired
    UserAddressMapper useraddressMapper;
    @Override
    public List<UserAddress> myaddress(String uid){
        UserAddressExample useraddressExample1 = new UserAddressExample();
        UserAddressExample.Criteria criteria1 = useraddressExample1.createCriteria();
        criteria1.andUserUidEqualTo(uid);
        List<UserAddress> result = useraddressMapper.selectByExample(useraddressExample1);
        return result;
    }
    @Override
    public Boolean edit(UserAddress userAddress) {
        Boolean result = true;
        UserAddressExample useraddressExample1 = new UserAddressExample();
        UserAddressExample.Criteria criteria1 = useraddressExample1.createCriteria();
        criteria1.andIdEqualTo(userAddress.getId());
        useraddressMapper.updateByExampleSelective(userAddress,useraddressExample1);
        result = true;
        return result;
    }
}
