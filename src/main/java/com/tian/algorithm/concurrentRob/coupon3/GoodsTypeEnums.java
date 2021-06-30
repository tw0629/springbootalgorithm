package com.tian.algorithm.concurrentRob.coupon3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author David Tian
 * @desc
 * @since 2021/6/30 11:11
 */
public enum GoodsTypeEnums {
    FOOD("FOOD"),
    CLOTH("CLOTH"),
    CAR("CAR"),
            ;
    GoodsTypeEnums(String goodsType) {
        this.goodsType = goodsType;
    }

    private String goodsType;

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public static List<String> goodsTypeList(){
        return Arrays.stream(GoodsTypeEnums.values()).map(GoodsTypeEnums::getGoodsType).collect(Collectors.toList());
    }


}
