package com.tian.algorithm.classical.concurrentRob.redPackage;

/**
 * @author David Tian
 * @desc
 * @since 2020-09-02 20:17
 */
public class RedPackage {

    private String id;

    private int size;

    private double money;

    public RedPackage(int size, double money) {
        this.size = size;
        this.money = money;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
