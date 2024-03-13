package com.hdy.laze_to_create_packge_for_leetcode.unRulerableArrary;

public class Main {
    public static void main(String[] args) {
      int[][] irregularArray = new int[3][]; // 创建一个长度为3的二维数组

        irregularArray[0] = new int[2]; // 第一个一维数组长度为2
        irregularArray[1] = new int[4]; // 第二个一维数组长度为4
        irregularArray[2] = new int[3]; // 第三个一维数组长度为3

        // 初始化数组
        for (int i = 0; i < irregularArray.length; i++) {
            for (int j = 0; j < irregularArray[i].length; j++) {
                irregularArray[i][j] = i + j; // 只是一个示例值
            }
        }

        // 打印数组内容
        for (int i = 0; i < irregularArray.length; i++) {
            for (int j = 0; j < irregularArray[i].length; j++) {
                System.out.print(irregularArray[i][j] + " ");
            }
            System.out.println();
        }
    }
}
