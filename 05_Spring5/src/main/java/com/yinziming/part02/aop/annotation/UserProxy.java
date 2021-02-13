package com.yinziming.part02.aop.annotation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class UserProxy {
    @Pointcut("execution(* com.yinziming.part02.aop.annotation.User.add())")
    public void point() {//公共切入点抽取
    }

    @Before("point()")
    public void before() {
        System.out.println("UserProxy.before()");
    }

    @After("point()")
    public void after() {
        System.out.println("UserProxy.after()");
    }

    @AfterReturning("point()")
    public void afterReturning() {
        System.out.println("UserProxy.afterReturning()");
    }

    @AfterThrowing("point()")
    public void afterThrowing() {
        System.out.println("UserProxy.afterThrowing()");
    }

    @Around(("point()"))
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("UserProxy.around() begin");
        Object proceed = proceedingJoinPoint.proceed();
        System.out.println("UserProxy.around() end");
        return proceed;
    }
}
