package com.distribution.transcation.twopc;

import com.distribution.transcation.Transaction;

/**
 * Transaction参与者
 * @author 张子宽
 * @date 2022/06/29
 */
public interface TwoPCTransactionMember extends Transaction {

    /**
     * 准备
     */
    boolean prepare();
}
