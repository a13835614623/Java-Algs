package com.distribution.raft.rpc;
/**
 * @description 投票请求RPC 响应
 * @author 张子宽
 * @date 2022/02/27
 * @copyright 
 */
public class VoteRpcResponse implements RpcResponse{

    /**
     * 候选人的任期号
     */
    private long term;
    /**
     * 如果候选人收到选票则为TRUE
     */
    private boolean voteGranted;

    public long getTerm() {
        return term;
    }

    public VoteRpcResponse setTerm(long term) {
        this.term = term;
        return this;
    }

    public boolean isVoteGranted() {
        return voteGranted;
    }

    public VoteRpcResponse setVoteGranted(boolean voteGranted) {
        this.voteGranted = voteGranted;
        return this;
    }
}
