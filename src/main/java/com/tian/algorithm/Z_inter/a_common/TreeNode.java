package com.tian.algorithm.Z_inter.a_common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author David Tian
 * @desc
 * @since 2021/8/1 22:45
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeNode {

    public TreeNode left;

    public TreeNode right;

    public Integer val;
}
