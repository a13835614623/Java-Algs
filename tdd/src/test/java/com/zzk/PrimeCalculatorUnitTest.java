package com.zzk;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrimeCalculatorUnitTest {

    @Test
    public void test() {
        assertEquals(List.of(), PrimeCalculator.calc(1));
        assertEquals(List.of(2), PrimeCalculator.calc(2));
        assertEquals(List.of(3), PrimeCalculator.calc(3));
        assertEquals(List.of(2, 2), PrimeCalculator.calc(4));
        assertEquals(List.of(2,3), PrimeCalculator.calc(6));
        assertEquals(List.of(2,2,2), PrimeCalculator.calc(8));
        assertEquals(List.of(3,3), PrimeCalculator.calc(9));
        assertEquals(List.of(2,5), PrimeCalculator.calc(10));
        assertEquals(List.of(11), PrimeCalculator.calc(11));
        assertEquals(List.of(2,2,3), PrimeCalculator.calc(12));
        assertEquals(List.of(3,5), PrimeCalculator.calc(15));
        assertEquals(List.of(2,2,2,2), PrimeCalculator.calc(16));
        assertEquals(List.of(2,3,3), PrimeCalculator.calc(18));
        assertEquals(List.of(2,3,3,3,41), PrimeCalculator.calc(2*3*3*3*41));
    }
}