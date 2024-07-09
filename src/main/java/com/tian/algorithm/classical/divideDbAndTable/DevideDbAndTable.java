package com.tian.algorithm.classical.divideDbAndTable;

import com.google.common.collect.Range;
import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author David Tian
 * @desc
 * @since 2021/7/17 09:32
 */
public class DevideDbAndTable {

    public static void main(String[] args) {

        List<String> databaseNames = Arrays.asList("ds0", "ds1");

        ValueRange valueRange = ValueRange.builder().lowerEndpoint(2000).upperEndpoint(4000).build();
        ShardingValue shardingValue = new ShardingValue(valueRange);

        Set<String> result = new LinkedHashSet<>();
        // 从sql 中获取 Between 2000 and 4000   的值，将2000 赋值给 lower,  4000 赋值给 upper
        int lower = shardingValue.getValueRange().getLowerEndpoint();
        int upper = shardingValue.getValueRange().getUpperEndpoint();
        for (int i = lower; i <= upper; i++) {
            for (String each : databaseNames) { //ds0,ds1
                if (each.endsWith(i % databaseNames.size() + "")) {
                    result.add(each);
                }
            }
        }

        System.out.println("=========");
        System.out.println("===>"+result.size());
        System.out.println("    ");
        System.out.println("===>"+result.toString());
    }

    @Test
    public void test() throws Exception{
        List<String> tableNames = Arrays.asList("table0","table1","table3");

        /*ValueRange valueRange = ValueRange.builder().lowerEndpoint(2000).upperEndpoint(4000).build();
        ShardingValue shardingValue = new ShardingValue(valueRange);*/
        Range<Integer> shardingValue = Range.closed(2000, 4000);

        Set<String> result = new LinkedHashSet<>();
        // 如果between  2000000 and 7000000
        if (Range.closed(1000,
                7000).encloses(shardingValue)) {
            for (String each : tableNames) {
                if (each.endsWith("0")) {
                    result.add(each);
                }
            }
        } else {
            //throw new UnsupportedOperationException();
            System.out.println("XXX ===> UnsupportedOperationException");
        }

        System.out.println("=========");
        System.out.println("===>"+result.size());
        System.out.println("    ");
        System.out.println("===>"+result.toString());

    }
}
