package com.distribution.transcation.tcc;
/**
 * 转账操作
 * @author 张子宽
 * @date 2022/06/29
 */
public class Transfer {

    /**
     * 源账户
     */
    private Account srcAccount;
    /**
     * 目标账户
     */
    private Account dstAccount;
    /**
     * A->B 转账金额
     */
    private int transferMoney;

    public Transfer(Account srcAccount, Account dstAccount, int transferMoney) {
        this.srcAccount = srcAccount;
        this.dstAccount = dstAccount;
        this.transferMoney = transferMoney;
    }


    public Account getSrcAccount() {
        return srcAccount;
    }

    public void setSrcAccount(Account srcAccount) {
        this.srcAccount = srcAccount;
    }

    public Account getDstAccount() {
        return dstAccount;
    }

    public void setDstAccount(Account dstAccount) {
        this.dstAccount = dstAccount;
    }

    public int getTransferMoney() {
        return transferMoney;
    }

    public void setTransferMoney(int transferMoney) {
        this.transferMoney = transferMoney;
    }
}
