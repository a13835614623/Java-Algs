package com.distribution.raft.rpc;

public interface RpcRequest<T extends RpcResponse> {

    /**
     * @description 请求的节点
     * @return com.distribution.raft.role.Node
     * @author 张子宽
     * @date 2022/02/27
     */
    long receiveNodeId();
}
