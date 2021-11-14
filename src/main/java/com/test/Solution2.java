package com.test;

public class Solution2 {
    public static int func1(int[] a_times, int[] p_times) {
        int res = 0;
        //窗口的状态，数字为0代表空闲，为正数代表还有多少分钟就空闲
        int state[] = {0, 0, 0, 0};
        for (int i = 0; i < a_times.length; i++) {
            int aTime = a_times[i];
            if (i>0){
                //更新窗口状态
                for (int j = 0; j < state.length; j++) {
                    if (state[j] != 0) {
                        state[j] -= aTime - a_times[i - 1];
                        if (state[j]<=0){
                            state[j]=0;
                        }
                    }
                }
            }
            int min = 0;
            for (int j = 0; j < state.length; j++) {
                //如果窗口空闲
                if (state[j] == 0) {
                    //取饭，该窗口设置为还有p_times[i]分钟空闲
                    state[j] += p_times[i];
                    break;
                }else {
                    if (state[min]>state[j]){
                        min=j;
                    }
                }
            }
            state[min]+=p_times[i];
            res+=min;
        }
        return res/a_times.length;
    }

    public static void main(String[] args) {
        int a_times[]={1,2,3,4,4,8};
        int p_times[]={50,20,11,25};
        func1(a_times,p_times);
    }
}
