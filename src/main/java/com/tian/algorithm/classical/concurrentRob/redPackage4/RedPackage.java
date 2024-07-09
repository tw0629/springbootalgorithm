package com.tian.algorithm.classical.concurrentRob.redPackage4;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.atomic.AtomicInteger;


/**
 * @author David Tian
 * @desc
 * @since 2021/6/30 15:34
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RedPackage {

    private Long redPackageId;

    private String userId;

    private String type;

    private AtomicInteger amount;

    private AtomicInteger quantity;

}
