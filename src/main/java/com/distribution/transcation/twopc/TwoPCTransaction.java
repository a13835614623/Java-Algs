package com.distribution.transcation.twopc;

import com.distribution.transcation.Transaction;

import java.util.List;

/**
 * 2pc
 * @author 张子宽
 * @date 2022/06/29
 */
public class TwoPCTransaction implements Transaction {
    /**
     * XA事务参与者
     */
    private List<TwoPCTransactionMember> transactionMembers;

    public TwoPCTransaction(List<TwoPCTransactionMember> transactionMembers) {
        this.transactionMembers = transactionMembers;
    }
    /**
     * 准备
     */
    public boolean prepare(){
        return transactionMembers.stream().allMatch(TwoPCTransactionMember::prepare);
    }

    @Override
    public void commit() {
        transactionMembers.forEach(TwoPCTransactionMember::commit);
    }

    @Override
    public void rollback() {
        transactionMembers.forEach(TwoPCTransactionMember::rollback);
    }
}
