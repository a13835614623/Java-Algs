package com.zzk;


import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PrimeCalculator {
    public static List<Integer> calc(int input) {
        return IntStream.range(2, input)
                .filter(x -> input % x == 0)
                .mapToObj(i -> Stream.concat(Stream.of(i), calc(input / i).stream()))
                .findFirst()
                .map(stream -> stream.collect(Collectors.toList()))
                .orElseGet(() -> input > 1 ? List.of(input) : List.of());
    }
}
