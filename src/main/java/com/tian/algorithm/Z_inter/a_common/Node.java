package com.tian.algorithm.Z_inter.a_common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author David Tian
 * @desc
 * @since 2021/8/1 20:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Node {

    public Node next;

    public Integer data;

    public Node(int data) {
        this.data = data;
    }

}
