package com.distribution.transcation;
/**
 * 事务
 * @author 张子宽
 * @date 2022/06/29
 */
public interface TransactionManager<T extends Transaction> {
    /**
     * 创建事务
     */
    T newTransaction();

}
