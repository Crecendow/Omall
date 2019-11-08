package com.txy.omall.service;

import com.txy.omall.model.ShiroUser;
import com.txy.omall.model.User;
import java.util.List;
import java.util.Set;


public interface IUser {
    public boolean isUserExist(String userName);
    public User getUserByName(String userName);
    public Set<String> findRoles(String userName);
    public Set<String> findPermissions(String userName);
    public ShiroUser getShiroUserByName (String userName);
}
