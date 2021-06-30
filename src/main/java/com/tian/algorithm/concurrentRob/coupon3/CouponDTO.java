package com.tian.algorithm.concurrentRob.coupon3;

import lombok.*;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author David Tian
 * @desc
 * @since 2021/6/30 10:25
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CouponDTO implements Comparator<CouponDTO> {

    private Long couponId;

    private Integer order;

    private String couponType;

    private String goodsType;

    private BigDecimal tradeAmount;

    private Date tradeDateStart;

    private Date tradeDateEnd;

    private String discountType;

    private BigDecimal discountMoney;

    private BigDecimal discountRatio;

    private AtomicInteger quantity;

    @Override
    public int compare(CouponDTO o1, CouponDTO o2) {

        if(this.getDiscountType().equals("RATIO")){
            return o1.getDiscountRatio().compareTo(o2.getDiscountRatio());
        }

        if(this.getDiscountType().equals("FIXED")){
            return o1.getDiscountMoney().compareTo(o2.getDiscountMoney());
        }

        return 1;
    }
}
