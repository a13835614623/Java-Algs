package com.distribution.transcation.tcc;

import java.util.Arrays;

/**
 * 转账操作
 * @author 张子宽
 * @date 2022/06/29
 */
public class AccountTransferService {
    /**
     * 执行转账操作,A->B money
     */
    public void transfer(Account A, Account B, int money) throws Exception {
        Transfer transfer = new Transfer(A, B, money);
        TCCTransactionManager tccTransactionManager = new TCCTransactionManager();
        TCCGlobalTransaction tccGlobalTransaction = tccTransactionManager.newTransaction();
        AccountIncreaseTransaction accountIncreaseTransaction = new AccountIncreaseTransaction(transfer, tccGlobalTransaction);
        AccountReduceTransaction accountReduceTransaction = new AccountReduceTransaction(transfer, tccGlobalTransaction);
        tccGlobalTransaction.setBranchTransactions(Arrays.asList(accountIncreaseTransaction,accountReduceTransaction));
        // 执行事务操作
        tccTransactionManager.run(tccGlobalTransaction);
    }

}
