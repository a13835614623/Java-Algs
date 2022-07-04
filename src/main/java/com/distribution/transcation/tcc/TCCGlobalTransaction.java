package com.distribution.transcation.tcc;

import com.distribution.transcation.Transaction;

import java.util.List;

/**
 * tcc全局事务
 * @author 张子宽
 * @date 2022/06/29
 */
public class TCCGlobalTransaction implements Transaction {

    /**
     * 全局事务id
     */
    private long txId;

    /**
     * 分支事务
     */
    private List<TccBranchTransaction> branchTransactions;

    public TCCGlobalTransaction() {
        txId = System.currentTimeMillis();
    }

    public TCCGlobalTransaction(long txId) {
        this.txId = txId;
    }

    public long getTxId() {
        return txId;
    }

    public void setTxId(long txId) {
        this.txId = txId;
    }

    public List<TccBranchTransaction> getBranchTransactions() {
        return branchTransactions;
    }

    public void setBranchTransactions(List<TccBranchTransaction> branchTransactions) {
        this.branchTransactions = branchTransactions;
    }

    @Override
    public void commit() {

    }

    @Override
    public void rollback() {

    }
}
