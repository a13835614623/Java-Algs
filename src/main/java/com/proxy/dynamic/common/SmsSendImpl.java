package com.proxy.dynamic.common;

import com.proxy.dynamic.common.ISmsSend;

/**
 * @description SmsSendImpl
 * @author 张子宽
 * @date 2022/02/20
 * @copyright 
 */
public class SmsSendImpl implements ISmsSend {
    @Override
    public void send(String message) {
        System.out.println("发短信:" + message);
    }
}
