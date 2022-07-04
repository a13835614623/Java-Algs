package com.distribution.transcation.twopc;

import com.distribution.transcation.service.OrderService;
import com.distribution.transcation.service.PayService;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {
        // 假设有两个服务,订单服务,支付服务
        TwoPCTransactionMember orderService = new OrderService();
        TwoPCTransactionMember payService = new PayService();
        TwoPCTransaction transaction = new TwoPCTransaction(Arrays.asList(orderService,payService));
        try {
            // 1. prepare 调用支付服务进行支付,支付成功后更新订单状态
            if (transaction.prepare()) {
                // 2.commit 提交事务
                transaction.commit();
            } else {
                // 2.rollback 回滚
                transaction.rollback();
            }
        } catch (Exception e) {
            // 2.rollback 回滚
            transaction.rollback();
        }
    }
}
