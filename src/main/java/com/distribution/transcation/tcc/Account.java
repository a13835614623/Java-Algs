package com.distribution.transcation.tcc;

/**
 * Account
 * @author 张子宽
 * @date 2022/06/29
 */
public class Account  {

    /**
     * 账户余额
     */
    private int money;
    /**
     * 账户名
     */
    private String name;

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * 增加金额
     */
    public void increaseMoney(int money) {
        this.money = this.money + money;
    }
    /**
     * 减少金额
     */
    public void reduceMoney(int money) {
        this.money = this.money - money;
    }

}
