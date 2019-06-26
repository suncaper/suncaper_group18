package org.group18.back.Service;

import org.group18.back.Entity.Cart;
import org.group18.back.Model.CartListModel;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public interface CartService {
    public Cart getCart(String user_uid, Integer goods_uid);
    public void addAndUpdateCart(Cart cart, String user_uid, Integer goods_uid, Integer specification_uid, Integer counts);
    public void increaseCart(String user_uid, Integer specification_uid);
    public void decreaseCart(String user_uid, Integer specification_uid);
    public boolean deleteCart(String user_uid, Integer goods_uid);
    public List<CartListModel> getCarts(String user_uid);
    public BigDecimal getTotalPrice(List<CartListModel> cartList);
    public Integer getTotalAmount(List<CartListModel> cartList);
    public Integer getTotalPoints(List<CartListModel> cartList);
    public Map<String, List<CartListModel>> getShopCarts (List<CartListModel> cartListModels);
}
