package com.zzk;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class PrimeCalculator {
    public static List<Integer> calc(int input) {
        List<Integer> result = new ArrayList<>();
        for (int i = 2; i <= input; i++) {
            if (input % i != 0) {
                continue;
            }
            result.add(i);
            result.addAll(calc(input / i));
            return result;
        }
        if (input > 1) {
            result.add(input);
        }
        return result;
    }
}
