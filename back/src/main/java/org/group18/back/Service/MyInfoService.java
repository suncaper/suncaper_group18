package org.group18.back.Service;

import org.group18.back.Entity.User;

import javax.servlet.http.Cookie;
import java.util.Map;

public interface MyInfoService {
    Map<String, String> myaddress();
    User judgeUserLoginStatus(String ticket);
    User checkTicket(Cookie[] cookies);
}