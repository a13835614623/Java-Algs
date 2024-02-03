package com.zzk;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrimeCalculatorUnitTest {

    @Test
    public void test(){
        assertEquals(List.of(),PrimeCalculator.calc(1));
    }
}