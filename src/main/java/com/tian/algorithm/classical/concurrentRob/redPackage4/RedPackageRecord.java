package com.tian.algorithm.classical.concurrentRob.redPackage4;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author David Tian
 * @desc
 * @since 2020-09-03 15:21
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RedPackageRecord{

    private String robUser;

    private BigDecimal money;

    private Long robTime;

    private Long redPackageId;

}
