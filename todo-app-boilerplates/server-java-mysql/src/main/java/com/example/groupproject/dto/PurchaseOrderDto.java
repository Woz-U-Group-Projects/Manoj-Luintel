package com.example.groupproject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class PurchaseOrderDto {

    private Long id;
    private String name;
    private LocalDate orderDate;
    private LocalDate receivedDate;

    public Long getId() {
        return id;
    }

    public PurchaseOrderDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PurchaseOrderDto setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public PurchaseOrderDto setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public LocalDate getReceivedDate() {
        return receivedDate;
    }

    public PurchaseOrderDto setReceivedDate(LocalDate receivedDate) {
        this.receivedDate = receivedDate;
        return this;
    }
}
