package com.distribution.raft;

/**
 * @description 节点类型
 * 在正常的情况下，只有一个服务器是 Leader，剩下的服务器是 Follower。Follower 是被动的，它们不会发送任何请求，只是响应来自 Leader 和 Candidate 的请求。
 * @author 张子宽
 * @date 2022/02/27
 */
public enum NodeType {
    /**
     * 负责发起心跳，响应客户端，创建日志，同步日志
     */
    LEADER,
    /**
     * Leader 选举过程中的临时角色，由 Follower 转化而来，发起投票参与竞选
     */
    FOLLOWER,
    /**
     * 接受 Leader 的心跳和日志同步数据，投票给 Candidate
     */
    CANDIDATE;

}
