package com.example.dynamicdatasourcedemo.aop;

import com.example.dynamicdatasourcedemo.config.DataSourceContextHolder;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 设置切面 执行具体方法选择的数据源
 * @author wls
 */
@Aspect
@Component
public class DataSourceAop {

    /**
     * 需要读的方法,切面
     */
    @Pointcut("!@annotation(com.example.dynamicdatasourcedemo.annotation.Master)" +
            "&& (execution(* com.example.dynamicdatasourcedemo.service..*.select*(..)) " +
            "|| execution(* com.example.dynamicdatasourcedemo.service..*.get*(..)))")
    public void readPointcut() {

    }

    /**
     * 写切面
     */
    @Pointcut("@annotation(com.example.dynamicdatasourcedemo.annotation.Master) " +
            "|| execution(* com.example.dynamicdatasourcedemo.service..*.insert*(..))" +
            "|| execution(* com.example.dynamicdatasourcedemo.service..*.save*(..))" +
            "|| execution(* com.example.dynamicdatasourcedemo.service..*.add*(..))" +
            "|| execution(* com.example.dynamicdatasourcedemo.service..*.update*(..))" +
            "|| execution(* com.example.dynamicdatasourcedemo.service..*.edit*(..))" +
            "|| execution(* com.example.dynamicdatasourcedemo.service..*.delete*(..))" +
            "|| execution(* com.example.dynamicdatasourcedemo.service..*.remove*(..))")
    public void writePointcut() {

    }

    @Before("readPointcut()")
    public void read() {
        DataSourceContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DataSourceContextHolder.master();
    }

    @After("readPointcut()")
    public void readAfter() {
        DataSourceContextHolder.clear();
    }

    @After("writePointcut()")
    public void writeAfter() {
        DataSourceContextHolder.clear();
    }
}
