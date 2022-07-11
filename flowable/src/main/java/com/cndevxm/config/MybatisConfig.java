package com.cndevxm.config;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScans(value = {
        @MapperScan(basePackages = "com/cndevxm/mapper"),
})
public class MybatisConfig {

}
