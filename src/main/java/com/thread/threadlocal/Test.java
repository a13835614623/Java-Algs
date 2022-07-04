package com.thread.threadlocal;

public class Test {

    private static final ThreadLocal<Integer> test = new ThreadLocal<>();

    public static void main(String[] args) {
        test.set(1);
        test.get();
        test.remove();
        new Thread(() -> {
            System.out.println("测试");
        });
    }
}
