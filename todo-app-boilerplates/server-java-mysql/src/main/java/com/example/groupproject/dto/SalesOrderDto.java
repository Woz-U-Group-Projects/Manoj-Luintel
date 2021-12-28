package com.example.groupproject.dto;

import java.time.LocalDate;

public class SalesOrderDto {
    private Long id;
    private String name;
    private LocalDate orderDate;
    private LocalDate shippingDate;

    public Long getId() {
        return id;
    }

    public SalesOrderDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public SalesOrderDto setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public SalesOrderDto setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public SalesOrderDto setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
        return this;
    }
}
