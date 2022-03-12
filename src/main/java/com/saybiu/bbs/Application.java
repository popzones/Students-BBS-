package com.saybiu.bbs;

import com.saybiu.bbs.security.SecurityProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ImportResource;


/**
 * @author wangfei
 */
@SpringBootApplication
@EnableConfigurationProperties(SecurityProperties.class)
@MapperScan("com.saybiu.bbs.dao")
@ImportResource(value = {"classpath:config/transaction.xml","classpath:config/interceptor.xml"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

}
