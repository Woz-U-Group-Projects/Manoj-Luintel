package com.example.groupproject.models;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.util.List;

public class PurchaseOrderModel {

    private Long id;
    private String name;
    private LocalDate orderDate;
    private LocalDate receivedDate;
    private PurchaseOrderStatus status;
    private List<Product> products;

    public Long getId() {
        return id;
    }

    public PurchaseOrderModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public PurchaseOrderModel setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public PurchaseOrderModel setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public LocalDate getReceivedDate() {
        return receivedDate;
    }

    public PurchaseOrderModel setReceivedDate(LocalDate receivedDate) {
        this.receivedDate = receivedDate;
        return this;
    }

    public PurchaseOrderStatus getStatus() {
        return status;
    }

    public PurchaseOrderModel setStatus(PurchaseOrderStatus status) {
        this.status = status;
        return this;
    }

    public List<Product> getProducts() {
        return products;
    }

    public PurchaseOrderModel setProducts(List<Product> products) {
        this.products = products;
        return this;
    }
}
