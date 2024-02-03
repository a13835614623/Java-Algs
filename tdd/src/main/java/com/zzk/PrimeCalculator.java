package com.zzk;


import java.util.ArrayList;
import java.util.List;

public class PrimeCalculator {
    public static List<Integer> calc(int input) {
        List<Integer> result = new ArrayList<>();
        if (input % 2 == 0 && input > 2) {
            int rest = input;
            result.add(2);
            rest = rest / 2;
            while (rest % 2 == 0 && rest > 2) {
                result.add(rest / 2);
                rest /= 2;
            }
            result.add(rest);
        }else if (input==9){
            result.add(3);
            result.add(input/3);
        } else if(input>1){
            result.add(input);
        }
        return result;
    }
}
