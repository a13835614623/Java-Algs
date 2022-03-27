package com.distribution.raft.client;

/**
 * @description 客户端的请求
 * @author 张子宽
 * @date 2022/03/05
 * @copyright 广州瑞云网络科技有限公司
 */
public interface ClientRequest {

    /**
     * @description 命令
     * @return com.distribution.raft.client.Command
     * @author 张子宽
     * @date 2022/03/05
     */
    Command command();
}
