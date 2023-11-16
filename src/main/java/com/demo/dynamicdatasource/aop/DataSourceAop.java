package com.demo.dynamicdatasource.aop;

import com.demo.dynamicdatasource.config.DataSourceContextHolder;
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
    @Pointcut("!@annotation(com.demo.dynamicdatasource.annotation.Master)" +
            "&& (execution(* com.demo.masterslavedemo.service..*.select*(..)) " +
            "|| execution(* com.demo.masterslavedemo.service..*.get*(..)))")
    public void readPointcut() {

    }

    /**
     * 写切面
     */
    @Pointcut("@annotation(com.demo.dynamicdatasource.annotation.Master) " +
            "|| execution(* com.demo.masterslavedemo.service..*.insert*(..))" +
            "|| execution(* com.demo.masterslavedemo.service..*.save*(..))" +
            "|| execution(* com.demo.masterslavedemo.service..*.add*(..))" +
            "|| execution(* com.demo.masterslavedemo.service..*.update*(..))" +
            "|| execution(* com.demo.masterslavedemo.service..*.edit*(..))" +
            "|| execution(* com.demo.masterslavedemo.service..*.delete*(..))" +
            "|| execution(* com.demo.masterslavedemo.service..*.remove*(..))")
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
