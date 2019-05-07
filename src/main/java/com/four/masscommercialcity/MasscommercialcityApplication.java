package com.four.masscommercialcity;

import com.github.pagehelper.PageHelper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

@SpringBootApplication
@MapperScan("com.four.masscommercialcity.mapper")
public class MasscommercialcityApplication {

    public static void main(String[] args) {
        SpringApplication.run(MasscommercialcityApplication.class, args);
    }
}
