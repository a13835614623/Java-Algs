package com.zzk;


import java.util.ArrayList;
import java.util.List;

public class PrimeCalculator {
    public static List<Integer> calc(int input) {
        List<Integer> result = new ArrayList<>();
        if (input == 1) {
            return result;
        }
        for (int i = 2; i <= input; i++) {
            int rest = input;
            if (rest % i != 0 || rest <= i) {
                continue;
            }
            result.add(i);
            rest = rest / i;
            while (rest % i == 0 && rest > i) {
                result.add(rest / i);
                rest /= i;
            }
            result.add(rest);
            return result;
        }
        result.add(input);
        return result;
    }
}
