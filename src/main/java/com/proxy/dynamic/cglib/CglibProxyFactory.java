package com.proxy.dynamic.cglib;

import com.proxy.dynamic.jdk.SmsSendInvocationHandler;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;

import java.lang.reflect.Proxy;

/**
 * @description CglibProxyFactory
 * http://javaguide.cn/java/basis/proxy/#_3-1-jdk-动态代理机制
 * @author 张子宽
 * @date 2022/02/20
 */
public class CglibProxyFactory {


    public static <T> T getProxy(Class<T> targetClass) {
        Enhancer enhancer = new Enhancer();
        enhancer.setClassLoader(targetClass.getClassLoader());
        enhancer.setSuperclass(targetClass);
        enhancer.setCallback(new SmsSendMethodInterceptor());
        return (T) enhancer.create();
    }
}
