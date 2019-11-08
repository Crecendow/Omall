package com.txy.omall;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("com.txy.omall.dao")
@SpringBootApplication
public class OmallApplication {

    public static void main(String[] args) {
        SpringApplication.run(OmallApplication.class, args);
    }

}
