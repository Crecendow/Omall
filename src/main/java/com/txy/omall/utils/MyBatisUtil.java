package com.txy.omall.utils;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;


public class MyBatisUtil {
    private static SqlSessionFactory sqlSessionFactory;
    static{
        String resource = "sqlMapConfig.xml";
        InputStream  in = null;
        try {
            in = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);

        }catch (Exception  e){
            e.printStackTrace();
        }finally {
            if(in != null){
                try {
                    in.close();
                }catch (IOException e){
                    e.printStackTrace();
                }

            }
        }

    }

    public static SqlSession getSession(){
        return sqlSessionFactory.openSession();
    }
}
