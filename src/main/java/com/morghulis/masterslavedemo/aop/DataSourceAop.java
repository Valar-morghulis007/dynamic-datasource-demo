package com.morghulis.masterslavedemo.aop;

import com.morghulis.masterslavedemo.config.DataSourceContextHolder;
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
    @Pointcut("!@annotation(com.morghulis.masterslavedemo.annotation.Master)" +
            "&& (execution(* com.morghulis.masterslavedemo.service..*.select*(..)) " +
            "|| execution(* com.morghulis.masterslavedemo.service..*.get*(..)))")
    public void readPointcut() {

    }

    /**
     * 写切面
     */
    @Pointcut("@annotation(com.morghulis.masterslavedemo.annotation.Master) " +
            "|| execution(* com.morghulis.masterslavedemo.service..*.insert*(..))" +
            "|| execution(* com.morghulis.masterslavedemo.service..*.save*(..))" +
            "|| execution(* com.morghulis.masterslavedemo.service..*.add*(..))" +
            "|| execution(* com.morghulis.masterslavedemo.service..*.update*(..))" +
            "|| execution(* com.morghulis.masterslavedemo.service..*.edit*(..))" +
            "|| execution(* com.morghulis.masterslavedemo.service..*.delete*(..))" +
            "|| execution(* com.morghulis.masterslavedemo.service..*.remove*(..))")
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
