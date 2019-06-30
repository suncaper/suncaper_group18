package org.group18.back.Service;

import org.group18.back.Entity.User;
import org.group18.back.Entity.UserAddress;
import org.group18.back.Entity.UserHistory;
import org.group18.back.Entity.Goods;
import org.group18.back.Model.HistroyGoodsModel;
import org.group18.back.Model.MyInfoPageModel;
import org.group18.back.Model.OrderPageModel;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface MyInfoService {
    List<UserAddress> myaddress(String uid);

    ArrayList<HistroyGoodsModel> getHistoryGoods(String uid);
    boolean isSameDate(Date date1, Date date2);
    Boolean edit(UserAddress userAddress);
    List<OrderPageModel> getOrderPageInfo(String userUid, int pageSize, int page);
    Long getUserOrderCount(String userUid);
    void deleteUserOrder(Integer orderId);
    void reviewGoods(Integer goodsUid, Integer specificationUid, String userUid, String payWay, String review);
    List<UserAddress> getUserAddressList(String userUid);
    void deleteUserAddress(Integer addressId);
    void editUserAddress(UserAddress userAddress);
}