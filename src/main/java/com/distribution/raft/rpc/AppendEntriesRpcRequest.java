package com.distribution.raft.rpc;

import java.util.List;

/**
 * @description 附加日志远程调用
 * @author 张子宽
 * @date 2022/02/27
 */
public class AppendEntriesRpcRequest extends AbstractRpcRequest<AppendEntriesRpcResponse> {
    /**
     * 领导人的任期号
     */
    private long term;
    /**
     * 领导人的 id，为了其他服务器能重定向到客户端
     */
    private long leaderId;
    /**
     * 最新日志之前的日志的索引值
     */
    private long prevLogIndex;
    /**
     * 最新日志之前的日志的领导人任期号
     */
    private long prevLogTerm;

    /**
     * 将要存储的日志条目（表示 heartbeat 时为空，有时会为了效率发送超过一条）
     */
    private List<Log> entries;
    /**
     * 领导人提交的日志条目索引值
     */
    private long leaderCommit;

    public AppendEntriesRpcRequest(long receiveNodeId) {
        super(receiveNodeId);
    }

    /**
     * @description 是否为心跳请求
     * @return boolean
     * @author 张子宽
     * @date 2022/03/05
     */
    public boolean isHeartbeat() {
        return entries == null || entries.size() == 0;
    }

    public long getTerm() {
        return term;
    }

    public AppendEntriesRpcRequest setTerm(long term) {
        this.term = term;
        return this;
    }

    public long getLeaderId() {
        return leaderId;
    }

    public AppendEntriesRpcRequest setLeaderId(long leaderId) {
        this.leaderId = leaderId;
        return this;
    }

    public long getPrevLogIndex() {
        return prevLogIndex;
    }

    public AppendEntriesRpcRequest setPrevLogIndex(long prevLogIndex) {
        this.prevLogIndex = prevLogIndex;
        return this;
    }

    public long getPrevLogTerm() {
        return prevLogTerm;
    }

    public AppendEntriesRpcRequest setPrevLogTerm(long prevLogTerm) {
        this.prevLogTerm = prevLogTerm;
        return this;
    }

    public List<Log> getEntries() {
        return entries;
    }

    public AppendEntriesRpcRequest setEntries(List<Log> entries) {
        this.entries = entries;
        return this;
    }

    public long getLeaderCommit() {
        return leaderCommit;
    }

    public AppendEntriesRpcRequest setLeaderCommit(long leaderCommit) {
        this.leaderCommit = leaderCommit;
        return this;
    }
}
