package com.test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestCreateTest {

    @Test
    void print() {
        // origin string
        System.out.println("TestFirstName");
        // to lowercase
        System.out.println("testfirstname");
        // to uppercase
        System.out.println("TESTFIRSTNAME");
        // to underline uppercase
        System.out.println("TEST_FIRST_NAME");
        // to underline lowercase
        System.out.println("test_first_name");
    }
}