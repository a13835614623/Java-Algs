package com.distribution.raft.rpc;

import com.distribution.raft.client.Command;


/**
 * @description LogImpl
 * @author 张子宽
 * @date 2022/03/05
 */
public class LogImpl implements Log{
    /**
     * 命令
     */
    private Command command;
    /**
     * 日志所处的任期
     */
    private long term;
    /**
     * 日志的索引
     */
    private long index;
    /**
     * 是否被提交
     */
    private boolean committed;

    public Command getCommand() {
        return command;
    }

    public LogImpl setCommand(Command command) {
        this.command = command;
        return this;
    }

    public long getTerm() {
        return term;
    }

    public LogImpl setTerm(long term) {
        this.term = term;
        return this;
    }

    public long getIndex() {
        return index;
    }

    public LogImpl setIndex(long index) {
        this.index = index;
        return this;
    }

    @Override
    public long term() {
        return term;
    }

    @Override
    public long index() {
        return index;
    }

    public LogImpl setCommitted(boolean committed) {
        this.committed = committed;
        return this;
    }

    @Override
    public boolean committed() {
        return committed;
    }
}
