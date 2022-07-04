package com.distribution.transcation.service;

import com.distribution.transcation.twopc.TwoPCTransactionMember;

/**
 * 订单服务
 * @author 张子宽
 * @date 2022/06/29
 */
public class OrderService implements TwoPCTransactionMember {


    public boolean updateOrderStatus(){
        return true;
    }

    @Override
    public boolean prepare() {
        return false;
    }

    @Override
    public void commit() {

    }

    @Override
    public void rollback() {

    }
}
