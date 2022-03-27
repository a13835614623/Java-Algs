package com.proxy.dynamic;

import com.proxy.dynamic.cglib.CglibProxyFactory;
import com.proxy.dynamic.common.ISmsSend;
import com.proxy.dynamic.common.SmsSendImpl;
import com.proxy.dynamic.jdk.JdkProxyFactory;

/**
 * @description ProxyTest
 * http://javaguide.cn/java/basis/proxy/#_3-1-jdk-动态代理机制
 * @author 张子宽
 * @date 2022/02/20
 */
public class ProxyTest {

    public static void main(String[] args) {
        testSendSms(JdkProxyFactory.getProxy(new SmsSendImpl()));
        testSendSms(CglibProxyFactory.getProxy(SmsSendImpl.class));
    }

    private static void testSendSms(Object Proxy) {
        ISmsSend proxy = (ISmsSend) Proxy;
        proxy.send("测试短信内容");
    }
}
