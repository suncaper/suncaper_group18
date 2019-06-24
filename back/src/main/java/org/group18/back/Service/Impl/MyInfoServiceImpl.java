package org.group18.back.Service.Impl;

import org.group18.back.Dao.TicketMapper;
import org.group18.back.Dao.UserAddressMapper;
import org.group18.back.Dao.UserMapper;
import org.group18.back.Entity.*;
import org.group18.back.Service.LoginRegisterService;
import org.group18.back.Utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.*;

//@Service
//public class MyInfoServiceImpl {
//
//    @Autowired
//    UserMapper userMapper;
//    @Autowired
//    TicketMapper ticketMapper;
//    public List<UserAddress> myaddress(String uid){
//        UserAddressExample UserAddressExample1 = new UserAddressExample();
//        UserAddressExample.Criteria criteria1 = UserAddressExample.createCriteria();
//        criteria1.andUserUidEqualTo(uid);
//        List<UserAddress> result = UserAddressMapper.selectByExample(UserAddressExample1);
//        return result;
//    }
//
//}
