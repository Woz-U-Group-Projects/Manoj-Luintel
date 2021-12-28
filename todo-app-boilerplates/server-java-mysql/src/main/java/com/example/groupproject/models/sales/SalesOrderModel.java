package com.example.groupproject.models.sales;

import com.example.groupproject.models.Product;
import com.example.groupproject.models.PurchaseOrderStatus;

import java.time.LocalDate;
import java.util.List;

public class SalesOrderModel {
    private Long id;
    private String name;
    private LocalDate orderDate;
    private LocalDate shippingDate;
    private SalesOrderStatus status;
    private List<Product> products;
    private double totalCost;

    public Long getId() {
        return id;
    }

    public SalesOrderModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public SalesOrderModel setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public SalesOrderModel setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public SalesOrderModel setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
        return this;
    }

    public SalesOrderStatus getStatus() {
        return status;
    }

    public SalesOrderModel setStatus(SalesOrderStatus status) {
        this.status = status;
        return this;
    }

    public List<Product> getProducts() {
        return products;
    }

    public SalesOrderModel setProducts(List<Product> products) {
        this.products = products;
        return this;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public SalesOrderModel setTotalCost(double totalCost) {
        this.totalCost = totalCost;
        return this;
    }
}
