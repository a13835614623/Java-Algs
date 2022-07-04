package com.loadbalance;

import java.util.List;

/**
 * 负载均衡
 * @author 张子宽
 * @date 2022/06/28
 */
public interface LoadBalance {

    /**
     * 选择
     * @param nodeList 节点列表
     * @param requestId 请求ID
     * @return com.loadbalance.Node
     * @author 张子宽
     * @date 2022/06/28
     */
    Node select(List<Node> nodeList,long requestId);
}
