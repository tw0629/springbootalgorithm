package com.tian.algorithm.classical.divideDbAndTable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author David Tian
 * @desc
 * @since 2021/7/17 09:33
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValueRange {

    private Integer lowerEndpoint;

    private Integer upperEndpoint;
}
