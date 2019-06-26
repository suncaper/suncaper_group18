package org.group18.back.Service;

import org.group18.back.Entity.UserAddress;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CheckoutService {
    List<UserAddress> getUserAddress(String user_uid);
}
