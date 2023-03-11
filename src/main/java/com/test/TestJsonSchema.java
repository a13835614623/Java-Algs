package com.test;

import java.util.List;

/**
 * 测试
 */
public class TestJsonSchema {

    /**
     * 测试int
     */
    private int testInt;
    /**
     * 测试Integer
     */
    private Integer testInteger;
    /**
     * 测试列表
     */
    private List<Integer> testList;
 
    /**
     * 测试int 数组
     */
    private int[] testIntArr;
    /**
     * 测试integer数组
     */
    private Integer[] testIntegerArr;
    /**
     * 测试对象嵌套
     */
    private User user;
    /**
     * 测试对象列表
     */
    private List<User> userList;
    /**
     * 测试对象数组
     */
    private User[] users;

    /**
     * 测试枚举
     */
    private SchemaType schemaType;


    public static class User{
        /**
         * 用户名
         */
        private String userName;
        /**
         * 年龄
         */
        private Integer age;
    }
}
