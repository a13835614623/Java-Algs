package com.distribution.raft.rpc;

import java.util.List;

/**
 * @description 附加日志远程调用
 * @author 张子宽
 * @date 2022/02/27
 */
public class AppendEntriesRpcResponse implements RpcResponse{
    /**
     * 当前的任期号，用于领导人更新自己的任期号
     */
    private long term;
    /**
     * 如果其它服务器包含能够匹配上 prevLogIndex 和 prevLogTerm 的日志时为真
     */
    private boolean success;


    public long getTerm() {
        return term;
    }

    public AppendEntriesRpcResponse setTerm(long term) {
        this.term = term;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public AppendEntriesRpcResponse setSuccess(boolean success) {
        this.success = success;
        return this;
    }
}
