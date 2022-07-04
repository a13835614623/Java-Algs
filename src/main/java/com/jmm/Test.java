package com.jmm;

import java.util.concurrent.TimeUnit;

public class Test {

    public static void main(String[] args) throws InterruptedException {
        int a = 1, b = 2;
        int c = a + b;
        System.out.println(c);
        TimeUnit.MINUTES.sleep(10);
    }
}
