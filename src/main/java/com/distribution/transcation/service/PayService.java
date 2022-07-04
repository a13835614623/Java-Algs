package com.distribution.transcation.service;

import com.distribution.transcation.twopc.TwoPCTransactionMember;

/**
 * 支付服务
 * @author 张子宽
 * @date 2022/06/29
 */
public class PayService implements TwoPCTransactionMember {

    public boolean pay(){

        return true;
    }

    @Override
    public boolean prepare() {
        return pay();
    }

    @Override
    public void commit() {

    }

    @Override
    public void rollback() {

    }
}
