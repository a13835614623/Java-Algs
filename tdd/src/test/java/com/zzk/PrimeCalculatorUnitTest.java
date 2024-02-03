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
    }
}