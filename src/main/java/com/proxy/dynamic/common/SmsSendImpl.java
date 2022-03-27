package com.proxy.dynamic.common;

import com.proxy.dynamic.common.ISmsSend;

/**
 * @description SmsSendImpl
 * @author 张子宽
 * @date 2022/02/20
 * @copyright 广州瑞云网络科技有限公司
 */
public class SmsSendImpl implements ISmsSend {
    @Override
    public void send(String message) {
        System.out.println("发短信:" + message);
    }
}
