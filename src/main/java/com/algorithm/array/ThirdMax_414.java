package com.algorithm.array;

/**
 * 给定一个非空数组，返回此数组中第三大的数。如果不存在，则返回数组中最大的数。要求算法时间复杂度必须是O(n)。
 *
 * 示例 1:
 *
 * 输入: [3, 2, 1]
 *
 * 输出: 1
 *
 * 解释: 第三大的数是 1.
 *
 * 示例 2:
 *
 * 输入: [1, 2]
 *
 * 输出: 2
 *
 * 解释: 第三大的数不存在, 所以返回最大的数 2 .
 *
 * 示例 3:
 *
 * 输入: [2, 2, 3, 1]
 *
 * 输出: 1
 *
 * 解释: 注意，要求返回第三大的数，是指第三大且唯一出现的数。
 * 存在两个值为2的数，它们都排第二。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/third-maximum-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Description:第三大的数
 * @Author: zzk
 * @Date: 2019-09-13 10:26
 */
public class ThirdMax_414 {
    public static void swap(int i,int j,int[] nums){
        int temp = nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }
    public static int thirdMax(int[] nums) {
        int max1=Integer.MIN_VALUE,max2=max1,max3=max2,n=nums.length,cnt=0;//具体看代码就知道啦
        for(int i=0;i<n;i++)max1=Math.max(max1,nums[i]);
        for(int i=0;i<n;i++)if(nums[i]!=max1)max2=Math.max(max2,nums[i]);
        for(int i=0;i<n;i++)if(nums[i]!=max1&&nums[i]!=max2){
            if(nums[i]>=max3){
                max3=nums[i];
                cnt++;
            }
        }
        return (cnt==0)?max1:max3;
    }
    public static void main(String[] args) {
        int nums[]={1,5,65,4,1,56,5,89,5};
        int nums2[]={1, 2};
        int nums3[]={2, 2, 3, 1};
//        System.out.println(thirdMax(nums));
//        System.out.println(thirdMax(nums2));
        System.out.println(thirdMax(nums3));
    }
}
