package com.distribution.raft.rpc;
/**
 * @description 投票请求
 * @author 张子宽
 * @date 2022/02/27
 * @copyright 
 */
public class VoteRpcRequest extends AbstractRpcRequest<VoteRpcResponse>{

    /**
     * 候选人的任期号
     */
    private long term;
    /**
     * 请求投票的候选人 id
     */
    private long candidateId;
    /**
     * 候选人最新日志条目的索引值
     */
    private long lastLogIndex;
    /**
     * 候选人最新日志条目对应的任期号
     */
    private long lastLogTerm;

    public VoteRpcRequest(long receiveNodeId) {
        super(receiveNodeId);
    }

    public long getTerm() {
        return term;
    }

    public VoteRpcRequest setTerm(long term) {
        this.term = term;
        return this;
    }

    public long getCandidateId() {
        return candidateId;
    }

    public VoteRpcRequest setCandidateId(long candidateId) {
        this.candidateId = candidateId;
        return this;
    }

    public long getLastLogIndex() {
        return lastLogIndex;
    }

    public VoteRpcRequest setLastLogIndex(long lastLogIndex) {
        this.lastLogIndex = lastLogIndex;
        return this;
    }

    public long getLastLogTerm() {
        return lastLogTerm;
    }

    public VoteRpcRequest setLastLogTerm(long lastLogTerm) {
        this.lastLogTerm = lastLogTerm;
        return this;
    }
}
