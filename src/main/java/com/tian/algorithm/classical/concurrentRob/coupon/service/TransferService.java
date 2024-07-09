package com.tian.algorithm.classical.concurrentRob.coupon.service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author David Tian
 * @desc
 * @since 2020-08-22 15:50
 */
public interface TransferService {

    List<BigDecimal> buyGoodByCoupon(String userId, String goodId, int goodNum);
}
