package com.txy.omall.test;

import com.txy.omall.test.relam.MyRelam1;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.authz.ModularRealmAuthorizer;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;


public class ShiroIniTest {

    //将获取到的数据源进行分解，一个基层的代码
    public static void main(String[] args) {
//        初始化securityManger
        DefaultSecurityManager securityManager = new DefaultSecurityManager(  );
//        设置身份验证的策略
        ModularRealmAuthenticator authenticator = new ModularRealmAuthenticator();
//        这个策略是叫做  至少有一个匹配的策略
        authenticator.setAuthenticationStrategy( new AtLeastOneSuccessfulStrategy() );

        securityManager.setAuthenticator( authenticator );
        ModularRealmAuthorizer authorizer = new ModularRealmAuthorizer(  );
//        用于解析对应的字符串到对应的permission权限
        authorizer.setPermissionResolver( new WildcardPermissionResolver() );
        securityManager.setAuthorizer( authorizer );
//        设置数据源
        securityManager.setRealm( new MyRelam1() );
//        将securityManger绑定上下文
        SecurityUtils.setSecurityManager( securityManager );


        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken( "test","123456" );
        try{
            subject.login(token);
            System.out.println( "success");

        }catch (AuthenticationException e){
//            e.printStackTrace();
            System.out.println( "用户名或者密码错误" );
        }

    }

}
