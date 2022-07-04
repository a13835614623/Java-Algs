package com.distribution.transcation;
/**
 * Transaction
 * @author 张子宽
 * @date 2022/06/29
 */
public interface Transaction {

    /**
     * 提交事务
     */
    void commit();

    /**
     * 回滚事务
     */
    void rollback();
}
