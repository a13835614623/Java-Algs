package com.distribution.raft.config;

import java.util.Random;

/**
 * @description 配置
 * @author 张子宽
 * @date 2022/02/27
 */
public class RaftConfig {

    /**
     * 最小选举超时时间
     */
    private long minElectionTimeout;

    /**
     * 最大选举超时时间
     */
    private long maxElectionTimeout;

    public long getElectionTimeout() {
        return (long) (minElectionTimeout + (maxElectionTimeout - minElectionTimeout) * Math.random());
    }

}
