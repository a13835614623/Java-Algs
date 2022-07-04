package com.distribution.transcation.tcc;

import com.distribution.transcation.Transaction;
import com.distribution.transcation.TransactionManager;

import java.util.List;

public class TCCTransactionManager  implements TransactionManager<TCCGlobalTransaction> {


    public void run(TCCGlobalTransaction tccGlobalTransaction){
        List<TccBranchTransaction> branchTransactions = tccGlobalTransaction.getBranchTransactions();
        try {
            // 1. 发起所有分支事务的try操作
            for (TccBranchTransaction branchTransaction : branchTransactions) {
                branchTransaction.Try();
            }
        }catch (Exception e){
            // 任何一个分支事务的try操作执行失败,TM将会发起所有分支事务的Cancel操作
            for (TccBranchTransaction branchTransaction : branchTransactions) {
                // Cancel 操作若执行失败，TM 会进行重试。
                cancelWithRetry(branchTransaction);
            }
        }
        // 2. 若Try操作全部成功,TM将会执行所有分支事务的confirm操作
        for (TccBranchTransaction branchTransaction : branchTransactions) {
            // Confirm 操作若执行失败,TM会执行重试
            cancelWithRetry(branchTransaction);
        }
    }

    private void confirmWithRetry(TccBranchTransaction branchTransaction) {
        try {
            branchTransaction.Confirm();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private void cancelWithRetry(TccBranchTransaction branchTransaction) {
        try {
            branchTransaction.Cancel();
        }catch (Exception ex){
            try {
                branchTransaction.Cancel();
            } catch (Exception ex2) {
                throw new RuntimeException(ex);
            }
        }
    }

    @Override
    public TCCGlobalTransaction newTransaction() {
        return new TCCGlobalTransaction();
    }
}
