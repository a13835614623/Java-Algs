package com.proxy.dynamic.jdk;

import java.lang.reflect.Proxy;

/**
 * @description JdkProxyFactory
 * http://javaguide.cn/java/basis/proxy/#_3-1-jdk-动态代理机制
 * @author 张子宽
 * @date 2022/02/20
 * @copyright 
 */
public class JdkProxyFactory {


    public static Object getProxy(Object target) {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new SmsSendInvocationHandler(target));
    }
}
