package com.proxy.dynamic.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @description SmsSendInvocationHanlder
 * @author 张子宽
 * @date 2022/02/20
 * @copyright 广州瑞云网络科技有限公司
 */
public class SmsSendInvocationHandler implements InvocationHandler {

    /**
     * 被代理对象
     */
    private Object target;

    public SmsSendInvocationHandler(Object target) {
        this.target = target;
    }
    /**
     * @description invoke
     * @param proxy 生成的代理对象
     * @param method 代理方法
     * @param args 代理方法的参数
     * @return java.lang.Object
     * @author 张子宽
     * @date 2022/02/20
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("---------调用JDK动态代理方法前--------");
        Object result = method.invoke(target, args);
        System.out.println("---------调用JDK动态代理方法后--------");
        return result;
    }
}
