package com.txy.omall.dao;

import com.txy.omall.model.ShiroUser;
import tk.mybatis.MyMapper;

import java.util.Set;

public interface ShiroUserMapper extends MyMapper<ShiroUser> {
    public Set<String> findRoles( String userName);

    public Set<String> findPermissions( String userName);

}
