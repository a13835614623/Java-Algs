package com.distribution.raft.rpc;

/**
 * @description 负责发起RPC请求
 * @author 张子宽
 * @date 2022/02/27
 * @copyright 
 */
public interface RpcInvoker {

    /**
     * @description 执行rpc请求
     * @param rpcRequest 请求
     * @return T
     * @author 张子宽
     * @date 2022/02/27
     */
    <T extends RpcResponse> T invoke(RpcRequest<T> rpcRequest);
}
