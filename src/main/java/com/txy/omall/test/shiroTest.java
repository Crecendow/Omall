package com.txy.omall.test;

import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

public class shiroTest {

    @Test
    public static void main(String[] args) {

        //读取ini文件
        IniRealm iniRealm = new IniRealm("classpath:shiro.ini");

        //将security文件绑定到上下文中
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(iniRealm);

        //获取向Security Manager提交请求的subject，而主体subject可以通过shiro提供的一个工具类SecurityUtils来获取 SecurityUtils.setSecurityManager(defaultSecurityManager);//使用SecurityUtils之前要设置Security Manager环境 Subject subject = SecurityUtils.getSubject(); //3.主体Subject提交请求给Security Manager --> subject.login(token); UsernamePasswordToken token = new UsernamePasswordToken("xiehuaxin","123456");//提交请求时需要一个token，所以要先创建token subject.login(token); //4. shiro提供了一个检查主体subject是否认证的方法isAuthenticated(),此方法的返回结果是一个boolean值 System.out.println(subject.isAuthenticated());
        SecurityUtils.setSecurityManager(defaultSecurityManager);//使用SecurityUtils之前要设置Security Manager环境
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken( "test","000000" );
        try {
            subject.login(token);
            if(subject.isAuthenticated()){
                System.out.println( "登录成功 " );
                if(subject.hasRole( "admin" )){
                    System.out.println( "有admin角色" );
                }else {
                    System.out.println( "没有admin角色" );
                }

                if(subject.isPermitted( "del" )){
                    System.out.println( "拥有删除的权限" );
                }else{
                    System.out.println( "没有删除的权限" );
                }
            }
        }catch (Exception e){
            //e.printStackTrace();
            System.out.println( "登录异常，没有正确登录 ..." );
        }
    }
}
