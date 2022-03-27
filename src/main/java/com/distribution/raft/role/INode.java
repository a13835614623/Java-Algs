package com.distribution.raft.role;

import com.distribution.raft.NodeType;

/**
 * @description IRole
 * @author 张子宽
 * @date 2022/02/27
 */
public interface INode {

    /**
     * @description 节点类型
     * @return com.distribution.raft.NodeType
     * @author 张子宽
     * @date 2022/02/27
     */
    NodeType type();

    /**
     * @description 节点ID
     * @return long
     * @author 张子宽
     * @date 2022/03/05
     */
    long id();
}
