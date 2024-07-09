package com.tian.algorithm.classical.divideDbAndTable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author David Tian
 * @descv
 * @since 2021/7/17 09:33
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ShardingValue {
    private ValueRange valueRange;
}
