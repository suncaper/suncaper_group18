package org.group18.back.Service;

import org.group18.back.Entity.Cart;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface CartService {
    public Cart getCart(String user_uid, int goods_uid);
    public void addAndUpdateCart(Cart cart, String user_uid, int goods_uid, int specification_uid, int counts);
    public void increaseCart(Cart cart, String user_uid, int specification_uid);
    public Map<String, String> decreaseCart(Cart cart, String user_uid, int specification_uid);
    public boolean deleteCart(String user_uid, int goods_uid);
    public List<Cart> getCarts(String user_uid);
}
