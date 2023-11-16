package com.morghulis.masterslavedemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wls
 */
@SpringBootApplication
@MapperScan("com.morghulis.masterslavedemo.mapper")
public class MasterSlaveDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MasterSlaveDemoApplication.class, args);
    }

}
