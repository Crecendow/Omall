package com.txy.omall.test.relam;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

import javax.lang.model.element.NestingKind;

public class MyRelam1 implements Realm {
    public MyRelam1() {
        super();
    }

    @Override
    public String getName() {
        return "myrealm1";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        //限制数据源只支持只支持用户名、密码的token，支持哪种类型的token
        return authenticationToken instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String  username = (String) authenticationToken.getPrincipal();  //获得验证主体，这边指的是用户名
        String password = new String( (char[]) authenticationToken.getCredentials()); //把数组强制类型转换为String

        if (!"test".equals( username)){
            throw new UnknownAccountException(  );
        }

        if(!"123456".equals( password )){
            throw new IncorrectCredentialsException(  );
        }
        //返回一个认证的info的对象
        return new SimpleAuthenticationInfo(  username ,password ,getName() );
    }
}
