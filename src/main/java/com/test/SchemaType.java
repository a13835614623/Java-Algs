package com.test;


import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 类型
 * @author 张子宽
 * @date 2022/09/20
 */
public enum SchemaType {
    /**
     * 类型参数
     */
    STRING("string", "字符串", Lists.newArrayList(String.class)),
    NUMBER("number", "数字", Lists.newArrayList(Double.class, Long.class, double.class, long.class)),
    INTEGER("integer", "整数", Lists.newArrayList(Integer.class, Short.class, Byte.class,
            int.class, short.class, byte.class)),
    OBJECT("object", "对象", Lists.newArrayList()),
    ARRAY("array", "数组", Lists.newArrayList(Collection.class)),
    BOOLEAN("boolean", "布尔值", Lists.newArrayList(Boolean.class, boolean.class)),
    NULL("null", "null", Lists.newArrayList());

    /**
     * 值
     */
    private final String value;
    /**
     * 描述
     */
    private final String desc;

    private final List<Class<?>> clazz;

    private static final Map<String, SchemaType> ENUM_MAP = Arrays.stream(SchemaType.values())
            .collect(Collectors.toMap(SchemaType::getValue, Function.identity()));

    SchemaType(String value, String desc, List<Class<?>> clazz) {
        this.value = value;
        this.desc = desc;
        this.clazz = clazz;
    }

    public List<Class<?>> getClazz() {
        return clazz;
    }

    public String getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * 根据值获取枚举
     *
     * @param value 值
     * @return OrderManualReviewRuleReason
     * @author 张子宽
     * @date 2022/8/14
     */
    public static SchemaType parse(String value) {
        if (value == null) {
            return null;
        }
        return ENUM_MAP.get(value);
    }
    public static void main(String[] args) {
        Schema schema = new Schema();
        schema.setType(STRING);
        System.out.println(JSON.toJSONString(schema));
    }
}
