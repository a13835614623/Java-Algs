package com.zzk;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrimeCalculatorUnitTest {

    @Test
    public void test(){
        assertEquals(List.of(),PrimeCalculator.calc(1));
        assertEquals(List.of(2),PrimeCalculator.calc(2));
    }
}