package com.algorithm.array;

/**
 * @Description:
 * @Author: zzk
 * @Date: 2019-05-02 09:57
 */
public class MaxProfit_122 {
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        return fun(prices, 0, prices.length - 1);
    }

    public static int fun(int[] prices, int start, int end) {
        int price = 0;
        while (start < end && prices[start + 1] <= prices[start]) {
            start++;
        }
        while (start < end && prices[end - 1] >= prices[end]) {
            end--;
        }
        if (start == end) return 0;
        int left = start;
        for (int i = start + 1; i <= end - 1; i++) {
            if (prices[i] > prices[left])
                price = prices[left = i] - prices[start];
            if (prices[i] > prices[i + 1])
                return price + fun(prices, i + 1, end);

        }
        return prices[end] - prices[start];
    }
    public static int maxProfit2(int[] prices) {
        if (prices == null || prices.length == 0) return 0;
        int price=0;
        for (int i = 0; i < prices.length-1; i++) {
            int addPrice=prices[i+1]-prices[i];
            if(addPrice<0) price+=addPrice;
        }
        return price;
    }
    public static void main(String[] args) {
//        int prices[] = {7, 1, 5, 0, 3, 6, 4, 7, 8, 1, 9};
        int prices[] ={7,1,5,3,6,4,4};
//        int prices[] = {1, 2, 3, 4, 5};

        System.out.println(maxProfit2(prices));
    }
}
