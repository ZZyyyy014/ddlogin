package com.tx.dllogin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.tx.dllogin.dao")
@SpringBootApplication
public class DlloginApplication {
    public static void main(String[] args) {
        SpringApplication.run(DlloginApplication.class, args);
    }
}
