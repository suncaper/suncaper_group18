package org.group18.back.Model;

import org.group18.back.Entity.User;
import org.group18.back.Entity.UserAddress;

import java.util.List;

public class MyInfoPageModel {
    private User user;
    private List<UserAddress> userAddresses;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<UserAddress> getUserAddresses() {
        return userAddresses;
    }

    public void setUserAddresses(List<UserAddress> userAddresses) {
        this.userAddresses = userAddresses;
    }
}
