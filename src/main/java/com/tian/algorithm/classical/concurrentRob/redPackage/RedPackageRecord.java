package com.tian.algorithm.classical.concurrentRob.redPackage;

/**
 * @author David Tian
 * @desc
 * @since 2020-09-03 15:21
 */
public class RedPackageRecord {

    private String userId;

    private Double money;

    private long timeLong;

    public RedPackageRecord(String userId, Double money, long timeLong) {
        this.userId = userId;
        this.money = money;
        this.timeLong = timeLong;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public long getTimeLong() {
        return timeLong;
    }

    public void setTimeLong(long timeLong) {
        this.timeLong = timeLong;
    }

    @Override
    public String toString() {
        return "RedPackageRecord{" +
                "userId='" + userId + '\'' +
                ", money=" + money +
                ", timeLong=" + timeLong +
                '}';
    }
}
