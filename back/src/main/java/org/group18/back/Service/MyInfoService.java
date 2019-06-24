package org.group18.back.Service;

import org.group18.back.Entity.User;
import org.group18.back.Entity.UserAddress;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import java.util.List;

public interface MyInfoService {
    List<UserAddress> myaddress(String uid);
//    User judgeUserLoginStatus(String ticket);
//    User checkTicket(Cookie[] cookies);
}