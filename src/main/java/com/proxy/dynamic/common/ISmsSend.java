package com.proxy.dynamic.common;

/**
 * @description 发送短信接口
 * 代理接口
 * @author 张子宽
 * @date 2022/02/20
 */
public interface ISmsSend {

    /**
     * @description 发送短信
     * @param message 短信内容
     * @return void
     * @author 张子宽
     * @date 2022/02/20
     */
    void send(String message);
}
