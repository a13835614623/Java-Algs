package com.distribution.raft.rpc;

/**
 * @description AbstractRpcRequest
 * @author 张子宽
 * @date 2022/02/27
 * @copyright 广州瑞云网络科技有限公司
 */
public class AbstractRpcRequest<T extends RpcResponse> implements RpcRequest<T>{

    /**
     * 接收的节点的ID
     */
    private long receiveNodeId;

    public AbstractRpcRequest(long receiveNodeId) {
        this.receiveNodeId = receiveNodeId;
    }

    public AbstractRpcRequest<T> setReceiveNodeId(long receiveNodeId) {
        this.receiveNodeId = receiveNodeId;
        return this;
    }

    @Override
    public long receiveNodeId() {
        return receiveNodeId;
    }
}
