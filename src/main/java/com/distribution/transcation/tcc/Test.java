package com.distribution.transcation.tcc;

import com.distribution.transcation.service.OrderService;
import com.distribution.transcation.service.PayService;
import com.distribution.transcation.twopc.TwoPCTransaction;
import com.distribution.transcation.twopc.TwoPCTransactionMember;

import java.util.Arrays;
/**
 * 举例，场景为 A 转账 30 元给 B，A 和 B 账户在不同的服务。
 * -----------------------------------------------------------
 * 账户 A
 * try:
 *     检查余额是否够30元
 *     扣减30元
 * confirm:
 *     空
 * cancel:
 *     增加30元
 * -----------------------------------------------------------
 * 账户 B
 * try:
 *     增加30元
 * confirm:
 *     空
 * cancel:
 *     减少30元
 * -----------------------------------------------------------
 * @author 张子宽
 * @date 2022/06/29
 */
public class Test {


    public static void main(String[] args) {
        // 假设执行转账操作
        TCCTransactionManager tccTransactionManager = new TCCTransactionManager();
    }

}
