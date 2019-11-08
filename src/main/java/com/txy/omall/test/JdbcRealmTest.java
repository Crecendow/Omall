package com.txy.omall.test;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;


public class JdbcRealmTest {
    DruidDataSource dataSource = new DruidDataSource();

    {
        dataSource.setUrl("jdbc:mysql://localhost:3306/zmall");
        dataSource.setUsername("root");
        dataSource.setPassword("123123");
    }

    @Test
    public void testJDBC() {
        //1.创建jdbcReaml对象
        JdbcRealm jdbcRealm = new JdbcRealm();
        //2.设置数据源
        jdbcRealm.setDataSource(dataSource);
        //这里需要注意的是，在使用JdbcRealm的时候要设置权限的开关，只有设置为true时才会去查询权限数据
        jdbcRealm.setPermissionsLookupEnabled(true);
        //使用自定义的表/sql
        String sql = "select password from test_user where user_name = ?";
        jdbcRealm.setAuthenticationQuery(sql);
        String roleSql = "select role_name from test_user_role where user_name = ?";
        jdbcRealm.setUserRolesQuery(roleSql);


        //1.构建Security Manager环境（Security Manager是用来提供安全服务的，所以在做shiro认证的时候要先创建此对象,创建Security Manager对象之后要设置Realm）
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(jdbcRealm);
        //2.获取向Security Manager提交请求的subject，而   主体subject可以通过shiro提供的一个工具类SecurityUtils来获取
        SecurityUtils.setSecurityManager(defaultSecurityManager); //使用SecurityUtils之前要设置Security Manager环境
        Subject subject = SecurityUtils.getSubject();
        //3.主体Subject提交请求给Security Manager --> subject.login(token);
        UsernamePasswordToken token = new UsernamePasswordToken("xiehuaxin","123456");//提交请求时需要一个token，所以要先创建token
        subject.login(token);
        // 4. shiro提供了一个检查主体subject是否认证的方法isAuthenticated(),此方法的返回结果是一个boolean值
        System.out.println(subject.isAuthenticated());
    }}
