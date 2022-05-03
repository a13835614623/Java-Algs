package com.algorithm;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @description EraseOverlapIntervals_435
 * 给定一个区间的集合 intervals ，其中 intervals[i] = [starti, endi] 。返回 需要移除区间的最小数量，使剩余区间互不重叠 。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * 输出: 1
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 * 示例 2:
 *
 * 输入: intervals = [ [1,2], [1,2], [1,2] ]
 * 输出: 2
 * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 * 示例 3:
 *
 * 输入: intervals = [ [1,2], [2,3] ]
 * 输出: 0
 * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/non-overlapping-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 张子宽
 * @date 2022/05/01
 */
public class EraseOverlapIntervals_435 {
    static
    class Solution {
        public int eraseOverlapIntervals(int[][] intervals) {
            Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
            int len = intervals.length;
            int remove = 0;
            int[] last = intervals[0];
            for (int i = 1; i < len; i++) {
                int[] cur = intervals[i];
                if (isOverlap(last, cur)) {
                    // 保留区间右边最小的
                    if (last[1] > cur[1]) {
                        // 保留当前的
                        last = cur;
                    }
                    remove++;
                } else {
                    last = cur;
                }
            }
            return remove;
        }


        public boolean isOverlap(int[] a, int[] b) {
            return a[1] > b[0];
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.eraseOverlapIntervals(new int[][]{
                {1,2},
                {2,3},
                {1,3}
        }));
    }
}
