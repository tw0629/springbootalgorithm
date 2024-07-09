package com.tian.algorithm.classical.concurrentRob.redPackage3;

import com.google.common.util.concurrent.AtomicDouble;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author David Tian
 * @desc
 * @since 2020-09-02 20:17
 */
public class RedPackage3 {

    private String id;

    private AtomicInteger size;

    private AtomicDouble money;

    public RedPackage3(int size, double money) {
        this.size = new AtomicInteger(size);
        this.money = new AtomicDouble(money);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public AtomicInteger getSize() {
        return size;
    }

    public void setSize(AtomicInteger size) {
        this.size = size;
    }

    public AtomicDouble getMoney() {
        return money;
    }

    public void setMoney(AtomicDouble money) {
        this.money = money;
    }


}
