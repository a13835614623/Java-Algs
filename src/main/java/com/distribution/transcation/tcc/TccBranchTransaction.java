package com.distribution.transcation.tcc;

import com.distribution.transcation.Transaction;

/**
 * Tcc分支事务
 * @author 张子宽
 * @date 2022/06/29
 */
public interface TccBranchTransaction<T> extends Transaction {

    /**
     * 主要是对业务系统做检测及资源预留
     */
    void Try() throws Exception;
    /**
     * 主要是对业务系统做确认提交，Try阶段执行成功并开始执行 Confirm阶段时，默认 Confirm阶段是不会出错的。即：只要Try成功，Confirm一定成功。
     */
    void Confirm() throws Exception;
    /**
     * 主要是在业务执行错误，需要回滚的状态下执行的业务取消，预留资源释放
     */
    void Cancel();
    /**
     * 全局的事务ID
     */
    long globalTxId();
    /**
     * 分支事务ID
     */
    long branchTxId();
}
