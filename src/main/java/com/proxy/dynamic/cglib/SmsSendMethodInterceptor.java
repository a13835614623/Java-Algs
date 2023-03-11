package com.proxy.dynamic.cglib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
/**
 * @description GCLIB拦截器
 * @author 张子宽
 * @date 2022/02/20
 */
public class SmsSendMethodInterceptor implements MethodInterceptor {


    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("---------调用cglib动态代理方法前--------");
        Object result = methodProxy.invokeSuper(o, args);
        System.out.println("---------调用cglib动态代理方法后--------");
        return result;
    }
}
