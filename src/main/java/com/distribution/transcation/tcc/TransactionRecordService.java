package com.distribution.transcation.tcc;
/**
 * 事务记录service
 * @author 张子宽
 * @date 2022/06/29
 */
public class TransactionRecordService {


    public void saveOrUpdateTransactionStatus(TccBranchTransaction transaction, TCCTransactionStatus status){
        TransactionRecord transactionRecord = new TransactionRecord();
        transactionRecord.setBranchTransactionId(transaction.branchTxId());
        transactionRecord.setGlobalTransactionId(transaction.globalTxId());
        transactionRecord.setStatus(status);
        insertOrUpdate(transactionRecord);
    }

    public TCCTransactionStatus get(TccBranchTransaction branchTransaction){
        TransactionRecord record = selectByGlobalTxIdAndBranchTxId(branchTransaction.globalTxId(),branchTransaction.globalTxId());
        return record==null?null:record.getStatus();
    }

    /**
     * 根据事务ID查询记录
     */
    private TransactionRecord selectByGlobalTxIdAndBranchTxId(long globalTxId, long globalTxId1) {
        return null;
    }

    /**
     * 插入或者更新
     */
    private void insertOrUpdate(TransactionRecord transactionRecord) {
        // 暂不实现
    }

}
