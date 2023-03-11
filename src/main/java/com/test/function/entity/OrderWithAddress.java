package com.test.function.entity;

import lombok.Data;

@Data
public class OrderWithAddress {

    private Long oid;

    Address address;
}
