package com.txy.omall.serviceImpl;//package com.txy.zmall.serviceImpl;

import com.txy.omall.dao.ShiroUserMapper;
import com.txy.omall.dao.UserMapper;
import com.txy.omall.model.ShiroUser;
import com.txy.omall.model.User;
import com.txy.omall.service.IUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserImpl implements IUser {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ShiroUserMapper shiroUserMapper;

    @Override
    public boolean isUserExist(String userName){
        List<User> userList = userMapper.selectAll();
        if(userList!=null){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public  User getUserByName(String userName){
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userName",userName);
//        List<User> userList = userMapper.selectByExample(example);
        User user= userMapper.selectOneByExample(example);
        return user;
    }

    @Override
    public Set<String> findRoles(String userName){
        Set<String> roleSet = new HashSet<String>(  );
        roleSet = shiroUserMapper.findRoles( userName );
        return roleSet;
    }

    @Override
    public Set<String> findPermissions(String userName){
        Set<String> permissionSet = new HashSet<String>(  );
        permissionSet = shiroUserMapper.findPermissions( userName );
        return permissionSet;
    }


    @Override
    public ShiroUser getShiroUserByName (String userName){
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userName",userName);
//        List<User> userList = userMapper.selectByExample(example);
        ShiroUser shiroUser= shiroUserMapper.selectOneByExample(example);
        return shiroUser;
    }

}
