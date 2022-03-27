package com.distribution.raft.rpc;

/**
 * @description 日志
 * @author 张子宽
 * @date 2022/02/27
 */
public interface Log {
    /**
     * @description 日志所处的任期
     * @return long
     * @author 张子宽
     * @date 2022/02/27
     */
    long term();
    /**
     * @description 日志的索引
     * @return long
     * @author 张子宽
     * @date 2022/02/27
     */
    long index();

    /**
     * @description 是否为提交了
     * @return boolean
     * @author 张子宽
     * @date 2022/03/05
     */
    boolean committed();
}
