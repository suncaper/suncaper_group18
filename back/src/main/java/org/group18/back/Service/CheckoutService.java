package org.group18.back.Service;

import org.group18.back.Entity.UserAddress;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


public interface CheckoutService {
    public List<UserAddress> getUserAddress(String user_uid);
    public BigDecimal getUserBalance(String user_uid);
    public Integer getUserPoints(String user_uid);
}
