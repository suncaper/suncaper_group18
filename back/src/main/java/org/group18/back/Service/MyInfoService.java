package org.group18.back.Service;

import org.group18.back.Entity.User;
import org.group18.back.Entity.UserAddress;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.List;
import java.util.Map;

public interface MyInfoService {
    List<UserAddress> myaddress(String uid);
    Boolean edit(UserAddress userAddress);
//    User judgeUserLoginStatus(String ticket);
//    User checkTicket(Cookie[] cookies);
}