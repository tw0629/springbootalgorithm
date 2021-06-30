package com.tian.algorithm.concurrentRob.coupon.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author David Tian
 * @desc
 * @since 2020-08-22 15:36
 */
public class Coupon {

    private int couponId;

    private String couponType;

    /**
     * 蔬菜水果 衣服 电子产品
     */
    private List<String> goodTypes;

    private int num;

    private BigDecimal transactionAmount;

    private Date transactionTimeStart;

    private Date transactionTimeEnd;

    private String couponRule;

}
