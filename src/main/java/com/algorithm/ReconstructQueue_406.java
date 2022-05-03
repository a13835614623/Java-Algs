package com.algorithm;

import com.algorithm.array.util.ArrayUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @description ReconstructQueue_406
 * 假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。
 *
 * 请你重新构造并返回输入数组 people 所表示的队列。返回的队列应该格式化为数组 queue ，其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
 * 输出：[[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
 * 解释：
 * 编号为 0 的人身高为 5 ，没有身高更高或者相同的人排在他前面。
 * 编号为 1 的人身高为 7 ，没有身高更高或者相同的人排在他前面。
 * 编号为 2 的人身高为 5 ，有 2 个身高更高或者相同的人排在他前面，即编号为 0 和 1 的人。
 * 编号为 3 的人身高为 6 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
 * 编号为 4 的人身高为 4 ，有 4 个身高更高或者相同的人排在他前面，即编号为 0、1、2、3 的人。
 * 编号为 5 的人身高为 7 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
 * 因此 [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]] 是重新构造后的队列。
 * 示例 2：
 *
 * 输入：people = [[6,0],[5,0],[4,0],[3,2],[2,2],[1,4]]
 * 输出：[[4,0],[5,0],[2,2],[3,2],[1,4],[6,0]]
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：<a href="https://leetcode.cn/problems/queue-reconstruction-by-height">https://leetcode.cn/problems/queue-reconstruction-by-height</a>
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author 张子宽
 * @date 2022/05/03
 */
public class ReconstructQueue_406 {

    public static
    class Solution {
        public int[][] reconstructQueue(int[][] people) {
            Arrays.sort(people, (o1, o2) -> {
                if (o1[0] != o2[0]) {
                    return o2[0] - o1[0];
                }
                return o1[1] - o2[1];
            });
            ArrayUtil.printArray2(people);
            List<int[]> ans = new ArrayList<int[]>();
            for (int[] person : people) {
                // 这里相当于是插入,后面的元素会后移,因为插入的元素币后面所有的元素都小,因此不会影响结果
                ans.add(person[1], person);
            }
            return ans.toArray(new int[ans.size()][]);
        }

        public static int cmp(int[][] people, int index) {
            int[] person = people[index];
            int high = person[0];
            int overCount = person[1];
            for (int i = 0; i < index; i++) {
                if (people[i][0] >= high) {
                    overCount--;
                    if (overCount < 0) {
                        return -1;
                    }
                }
            }
            return overCount;
        }

        public static void swap(int[][] people, int a, int b) {
            int[] temp = people[a];
            people[a] = people[b];
            people[b] = temp;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        ArrayUtil.printArray2(solution.reconstructQueue(new int[][]{
//                {5, 0}, {7, 0}, {5, 2}, {6, 1}, {4, 4}, {7, 1}
//        }));
        solution.reconstructQueue(new int[][]{
                {6, 0}, {5, 0}, {4, 0}, {3, 2}, {2, 2}, {1, 4}
        });
    }
}
