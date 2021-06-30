package com.tian.algorithm.concurrentRob.coupon3;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author David Tian
 * @desc
 * @since 2021/6/30 10:55
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String userId;

    private String userName;

    private String goodsId;

    private String goodsType;

    private BigDecimal goodsAmount;

}
