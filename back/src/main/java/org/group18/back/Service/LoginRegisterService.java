package org.group18.back.Service;

import org.group18.back.Entity.User;

import javax.servlet.http.Cookie;
import java.util.Map;

public interface LoginRegisterService {
    Map<String, String> register(String userName, String password, String email);
    Map<String, String> login(String email, String password);
    User getUserInfoByTicket(String ticket);
    User checkLoginStatus(Cookie[] cookies);
}
