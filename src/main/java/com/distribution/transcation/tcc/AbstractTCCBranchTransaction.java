package com.distribution.transcation.tcc;
/**
 * AbstractTCCBranchTransaction
 * @author 张子宽
 * @date 2022/06/29
 */
public abstract class AbstractTCCBranchTransaction<T> implements TccBranchTransaction<T> {


    private long branchTxId;

    private long globalTxId;

    public AbstractTCCBranchTransaction(long branchTxId, long globalTxId) {
        this.branchTxId = branchTxId;
        this.globalTxId = globalTxId;
    }


    @Override
    public long branchTxId() {
        return branchTxId;
    }

    @Override
    public long globalTxId() {
        return globalTxId;
    }
}
