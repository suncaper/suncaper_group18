package org.group18.back.Service.Impl;

import org.group18.back.Dao.UserAddressMapper;
import org.group18.back.Entity.UserAddress;
import org.group18.back.Entity.UserAddressExample;
import org.group18.back.Service.CheckoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckoutServiceImpl implements CheckoutService{
    @Autowired
    private UserAddressMapper userAddressMapper;

    @Override
    public List<UserAddress> getUserAddress(String user_uid){
        UserAddressExample userAddressExample = new UserAddressExample();
        UserAddressExample.Criteria criteria = userAddressExample.createCriteria();
        criteria.andUserUidEqualTo(user_uid);
        List<UserAddress> userAddressList = userAddressMapper.selectByExample(userAddressExample);
        return userAddressList;
    }
}
