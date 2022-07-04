package com.distribution.transcation.tcc;
/**
 * 事务执行记录
 * @author 张子宽
 * @date 2022/06/29
 */
public class TransactionRecord {
    /**
     * 全局事务ID
     */
    private long globalTransactionId;
    /**
     * 分支事务ID
     */
    private long branchTransactionId;

    /**
     * 分支事务状态
     */
    private TCCTransactionStatus status;

    public TCCTransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TCCTransactionStatus status) {
        this.status = status;
    }

    public long getBranchTransactionId() {
        return branchTransactionId;
    }

    public void setBranchTransactionId(long branchTransactionId) {
        this.branchTransactionId = branchTransactionId;
    }

    public long getGlobalTransactionId() {
        return globalTransactionId;
    }

    public void setGlobalTransactionId(long globalTransactionId) {
        this.globalTransactionId = globalTransactionId;
    }
}
