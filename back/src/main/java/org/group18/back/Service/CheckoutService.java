package org.group18.back.Service;

import org.group18.back.Entity.UserAddress;
import org.group18.back.Model.CartListModel;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


public interface CheckoutService {
    public List<UserAddress> getUserAddress(String user_uid);
    public BigDecimal getUserBalance(String user_uid);
    public Integer getUserPoints(String user_uid);
    public Integer payment(String user_uid, BigDecimal totalPrice, Integer totalPoints, BigDecimal balance, Integer points, String checkout_method, Map<String, List<CartListModel>> shopCartList, List<CartListModel> cartList);
    public void generateOrder(String user_uid, String checkout_method, Map<String, List<CartListModel>> shopCartList, Integer addressId, Integer result);
}
