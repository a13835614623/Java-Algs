package com.distribution.raft.role;

import com.distribution.raft.NodeType;

/**
 * @description Follower
 * @author 张子宽
 * @date 2022/02/27
 */
public class Follower extends AbstractNode {

    /**
     * 它表示leader将要发送给该follower的下一条日志条目的索引
     */
    private long nextIndex;

    public Follower(long id, long nextIndex) {
        super(id, NodeType.FOLLOWER);
        this.nextIndex = nextIndex;
    }

    public Follower setNextIndex(long nextIndex) {
        this.nextIndex = nextIndex;
        return this;
    }

    public long getNextIndex() {
        return nextIndex;
    }
}
