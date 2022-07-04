package com.distribution.transcation.tcc;
/**
 * 减少金额的分支事务
 * @author 张子宽
 * @date 2022/06/29
 */
public class AccountReduceTransaction extends IdempotencyTccBranchTransaction<Transfer> {
    /**
     * 转账操作
     */
    private Transfer transfer;
    /**
     * 全局事务ID
     */
    private long globalTxId;

    public AccountReduceTransaction(Transfer transfer, TCCGlobalTransaction tccGlobalTransaction) {
        this.transfer = transfer;
        globalTxId = tccGlobalTransaction.getTxId();
    }


    @Override
    public void doTry() throws Exception {
        Account sourceAccount = transfer.getSrcAccount();
        if (sourceAccount.getMoney() < transfer.getTransferMoney()) {
            throw new Exception("账户A余额不足");
        }
        // 校验通过,执行转账操作
        sourceAccount.reduceMoney(transfer.getTransferMoney());
    }

    @Override
    public void doConfirm() {

    }

    @Override
    public void doCancel() {
        // 补偿,增加回来转账的金额
        transfer.getSrcAccount().increaseMoney(transfer.getTransferMoney());
    }

    @Override
    public long globalTxId() {
        return globalTxId;
    }
}
