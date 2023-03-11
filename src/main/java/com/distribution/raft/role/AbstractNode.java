package com.distribution.raft.role;

import com.distribution.raft.NodeType;
/**
 * @description 抽象节点
 * @author 张子宽
 * @date 2022/03/05
 * @copyright 
 */
public class AbstractNode implements INode{

    /**
     * 节点ID
     */
    private long id;
    /**
     * 节点类型
     */
    private NodeType type;

    public AbstractNode(long id, NodeType type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public NodeType type() {
        return type;
    }

    @Override
    public long id() {
        return id;
    }
}
