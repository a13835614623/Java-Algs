package com.algorithm.array;

/**
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * <p>
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票），设计一个算法来计算你所能获取的最大利润。
 * <p>
 * 注意你不能在买入股票前卖出股票。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 * <p>
 * 示例 2:
 * <p>
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 * @Description:买卖股票的最佳时机
 * @Author: zzk
 * @Date: 2019-05-01 13:56
 */
public class MaxProfit_121 {
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int start=0,end=prices.length-1;
        while (start < end && prices[start + 1] <= prices[start]) {
            start++;
        }
        while (start <end  && prices[end - 1] >= prices[end]) {
            end--;
        }
        int max=start,min=start;
        for (int i = start; i <= end; i++) {
            max=prices[i]>prices[max]?i:max;
            min=prices[i]<prices[min]?i:min;
        }
        if(min<max)return prices[max]-prices[min];
        else {
            int minLeft=0,maxRight=min;
            for (int i = start; i < max; i++) {
                minLeft=prices[i]<prices[minLeft]?i:minLeft;
            }
            for (int i = min+1; i <= end; i++) {
                maxRight=prices[i]>prices[maxRight]?i:maxRight;
            }
            return Math.max(prices[maxRight]-prices[min],prices[max]-prices[minLeft]);
        }
    }
    public static int maxProfit2(int[] prices) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice)
                minprice = prices[i];
            else if (prices[i] - minprice > maxprofit)
                maxprofit = prices[i] - minprice;
        }
        return maxprofit;
    }
    public static void main(String[] args) {
//        int prices[] = {7, 1, 5, 0, 3, 6, 4, 7, 8, 1, 9};
        int prices[] ={11,2,7,1,4};
        System.out.println(maxProfit2(prices));
    }
}
