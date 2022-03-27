package com.distribution.raft.role;

import com.distribution.raft.rpc.Log;

import java.util.LinkedList;
import java.util.List;
/**
 * @description Data
 * @author 张子宽
 * @date 2022/02/27
 */
public class Data {
    /**
     * 服务器最后知道的任期号（从0开始递增）
     */
    private long currentTerm;
    /**
     * 在当前任期内收到选票的候选人 id（如果没有就为 null）
     */
    private String votedFor;
    /**
     * 日志
     */
    private LinkedList<Log> logs;
    /**
     * 已知的被提交的最大日志条目的索引值（从0开始递增）
     */
    private long commitIndex;
    /**
     * 被状态机执行的最大日志条目的索引值（从0开始递增）
     */
    private long lastApplied;

    /**
     * 在领导人服务器上不稳定存在的：（在选举之后初始化的）
     */
    private List<Long> nextIndex;
    /**
     * 在领导人服务器上不稳定存在的：（在选举之后初始化的）
     */
    private List<Long> matchIndex;


    public long getCommitIndex() {
        return commitIndex;
    }

    public Data setCommitIndex(long commitIndex) {
        this.commitIndex = commitIndex;
        return this;
    }

    public long getLastApplied() {
        return lastApplied;
    }

    public Data setLastApplied(long lastApplied) {
        this.lastApplied = lastApplied;
        return this;
    }

    public List<Long> getNextIndex() {
        return nextIndex;
    }

    public Data setNextIndex(List<Long> nextIndex) {
        this.nextIndex = nextIndex;
        return this;
    }

    public List<Long> getMatchIndex() {
        return matchIndex;
    }

    public Data setMatchIndex(List<Long> matchIndex) {
        this.matchIndex = matchIndex;
        return this;
    }

    public long getCurrentTerm() {
        return currentTerm;
    }

    public Data setCurrentTerm(long currentTerm) {
        this.currentTerm = currentTerm;
        return this;
    }

    public String getVotedFor() {
        return votedFor;
    }

    public Data setVotedFor(String votedFor) {
        this.votedFor = votedFor;
        return this;
    }

    public LinkedList<Log> getLogs() {
        return logs;
    }

    public Data setLogs(LinkedList<Log> logs) {
        this.logs = logs;
        return this;
    }
}
