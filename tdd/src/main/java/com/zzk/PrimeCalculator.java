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
            if (input % i != 0 || input <= i) {
                continue;
            }
            result.add(i);
            result.addAll(calc(input / i));
            return result;
        }
        result.add(input);
        return result;
    }
}
