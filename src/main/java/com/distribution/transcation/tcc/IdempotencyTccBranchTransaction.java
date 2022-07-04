package com.distribution.transcation.tcc;
/**
 * 1.幂等性处理
 * 2.空回滚问题处理
 * 3.悬挂问题处理
 * @author 张子宽
 * @date 2022/06/29
 */
public abstract class IdempotencyTccBranchTransaction<T> extends AbstractTCCBranchTransaction<T>{


    private TransactionRecordService transactionRecordService;

    public IdempotencyTccBranchTransaction() {
        super(System.currentTimeMillis(), System.currentTimeMillis());
    }


    @Override
    public void Try() throws Exception {
        // 幂等性处理
        if (hasTry()){
            return;
        }
        // 悬挂处理,如果Cancel接口先执行了,就不能再次执行try了
        if (hasCancel()){
            return;
        }
        transactionRecordService.saveOrUpdateTransactionStatus(this,TCCTransactionStatus.TRY);
        // 记录一下执行了try步骤
        doTry();
    }

    @Override
    public void Confirm() throws Exception {
        // 幂等性处理
        if (hasConfirm()){
            return;
        }
        transactionRecordService.saveOrUpdateTransactionStatus(this,TCCTransactionStatus.CONFIRM);
        doConfirm();
    }

    @Override
    public void Cancel() {
        // 幂等性处理
        if (hasCancel()){
            return;
        }
        // 空回滚问题兼容,如果没有执行try就执行cancel,就不执行
        if (!hasTry()){
            return;
        }
        transactionRecordService.saveOrUpdateTransactionStatus(this,TCCTransactionStatus.CANCEL);
        doCancel();
    }

    @Override
    public long globalTxId() {
        return 0;
    }

    @Override
    public void commit() {

    }

    @Override
    public void rollback() {

    }

    private boolean hasCancel() {
        return false;
    }

    private boolean hasTry() {
        return false;
    }

    private boolean hasConfirm() {
        return false;
    }

    public abstract void doTry() throws Exception;

    public abstract void doConfirm();

    public abstract void doCancel();
}
