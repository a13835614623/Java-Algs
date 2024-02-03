package com.zzk;


import java.util.ArrayList;
import java.util.List;

public class PrimeCalculator {
    public static List<Integer> calc(int input) {
        List<Integer> result = new ArrayList<>();
        for (int i = 2; i <= input; i++) {
            if (input % i == 0 && input > i) {
                int rest = input;
                result.add(i);
                rest = rest / i;
                while (rest % i == 0 && rest > i) {
                    result.add(rest / i);
                    rest /= i;
                }
                result.add(rest);
                return result;
            }
        }
        if (input > 1) {
            result.add(input);
        }
        return result;
    }
}
