package com.saybiu.bbs.configurations;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @Author: 王飞（改成你自己的姓名）
 * @Date: 2021/5/2 10:45 下午 （日期和时间）
 */
@Configuration
public class ValidatorConfig {
    @Bean
    public Validator validator() {
        ValidatorFactory validatorFactory = Validation.byProvider(HibernateValidator.class)
                .configure()
                //failFast：true  快速失败返回模式(只要有一个验证失败，则返回)    false 普通模式(会校验完所有的属性，然后返回所有的验证失败信息)
                .addProperty("hibernate.validator.fail_fast", "false")
                .buildValidatorFactory();
        return validatorFactory.getValidator();
    }
}
