package org.group18.back.Service;

import org.group18.back.Entity.UserAddress;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;


public interface CheckoutService {
<<<<<<< HEAD:back/src/main/java/org/group18/back/Service/checkoutService.java
    public List<UserAddress> getUserAddress(String user_uid);
    public BigDecimal getUserBalance(String user_uid);
    public Integer getUserPoints(String user_uid);
=======
    List<UserAddress> getUserAddress(String user_uid);
>>>>>>> 3fb18dd0ce92fb84621a4cf35877a16e3c13c6c4:back/src/main/java/org/group18/back/Service/CheckoutService.java
}
