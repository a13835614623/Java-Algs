package com.zzk;


import java.util.List;

public class PrimeCalculator {
    public static List<Integer> calc(int input) {
        if (input > 1) {
            return List.of(input);
        }
        return List.of();
    }
}
