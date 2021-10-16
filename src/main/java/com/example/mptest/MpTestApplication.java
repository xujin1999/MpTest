package com.example.mptest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.example.mptest.mapper")
@SpringBootApplication
public class MpTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MpTestApplication.class, args);
    }

}
