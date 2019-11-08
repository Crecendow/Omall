package com.txy.omall.relam;


import com.txy.omall.model.ShiroUser;
import com.txy.omall.service.IUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;


public class UserRealm extends AuthorizingRealm {

    @Autowired
    private IUser userService;
    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        //在数据库中查询用户拥有的角色/权限
        authorizationInfo.setRoles(userService.findRoles(username));
        authorizationInfo.setStringPermissions(userService.findPermissions(username));
        return authorizationInfo;
    }


    /**
     * 验证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        ShiroUser shiroUser = userService.getShiroUserByName( username );
        if(shiroUser == null){
            throw new UnknownAccountException(); //没找到账号
        }


        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                shiroUser.getUserName(),
                shiroUser.getPassword(),
//                ByteSource.Util.bytes(shiroUser.getCredentialsSalt()), //salt = username+salt
                getName());


        return authenticationInfo;
    }
}
