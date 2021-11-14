package com.algorithm.array;

/**
 * @description NumberOfBoomerangs_447
 * 给定平面上 n 对 互不相同 的点 points ，其中 points[i] = [xi, yi] 。回旋镖 是由点 (i, j, k) 表示的元组 ，其中 i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。
 *
 * 返回平面上所有回旋镖的数量。
 *
 *
 * 示例 1：
 *
 * 输入：points = [[0,0],[1,0],[2,0]]
 * 输出：2
 * 解释：两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
 * 示例 2：
 *
 * 输入：points = [[1,1],[2,2],[3,3]]
 * 输出：2
 * 示例 3：
 *
 * 输入：points = [[1,1]]
 * 输出：0
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-boomerangs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author 张子宽
 * @date 2021/09/13
 */
public class NumberOfBoomerangs_447 {

    static class Solution {

        public int numberOfBoomerangs(int[][] points) {
            int length = points.length;
            int count = 0;
            int[][] distances = new int[length][length];
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < length; j++) {
                    if (j == i) {
                        continue;
                    }
                    for (int k = j + 1; k < length; k++) {
                        if (k == i) {
                            continue;
                        }
                        System.out.println(i + "," + j + "," + k);
                        if (isBoomerangs(points, distances, i, j, k)) {
                            count++;
                        }
                    }
                }
            }
            return count << 1;
        }

        public boolean isBoomerangs(int[][] points, int[][] distances, int i, int j, int k) {
            int distance1 = distance(points, distances, i, j);
            int distance2 = distance(points, distances, i, k);
            return distance1 == distance2;
        }

        public int distance(int[][] points, int[][] distances, int i, int j) {
            if (distances[i][j]!=0){
                return distances[i][j];
            }
            int a = points[i][0] - points[j][0];
            int b = points[i][1] - points[j][1];
            int i1 = a * a + b * b;
            distances[i][j] = distances[j][i] = i1;
            return i1;
        }
    }

    public static void main(String[] args) {
        int[][] ints = {{0, 0}, {1, 0}, {-1, 0}, {0, 1}, {0, -1}};
//        System.out.println(new Solution().isBoomerangs(ints[0], ints[1], ints[2]));
        System.out.println(new Solution().numberOfBoomerangs(ints));
    }
}
