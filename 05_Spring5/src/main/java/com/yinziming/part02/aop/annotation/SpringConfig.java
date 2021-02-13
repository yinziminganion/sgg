package com.yinziming.part02.aop.annotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "com.yinziming.part02.aop.annotation")
@EnableAspectJAutoProxy
public class SpringConfig {
}
