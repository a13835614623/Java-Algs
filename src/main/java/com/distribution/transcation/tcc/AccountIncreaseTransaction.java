package com.distribution.transcation.tcc;
/**
 * 增加金额的分支事务
 * @author 张子宽
 * @date 2022/06/29
 */
public class AccountIncreaseTransaction extends IdempotencyTccBranchTransaction<Transfer> {
    /**
     * 转账操作
     */
    private Transfer transfer;
    /**
     * 全局事务ID
     */
    private long globalTxId;

    public AccountIncreaseTransaction(Transfer transfer, TCCGlobalTransaction tccGlobalTransaction) {
        this.transfer = transfer;
        globalTxId = tccGlobalTransaction.getTxId();
    }

    @Override
    public void doTry() throws Exception {
        transfer.getDstAccount().increaseMoney(transfer.getTransferMoney());
    }

    @Override
    public void doConfirm() {

    }

    @Override
    public void doCancel() {
        transfer.getDstAccount().reduceMoney(transfer.getTransferMoney());
    }

    @Override
    public long globalTxId() {
        return globalTxId;
    }
}
