package com.example.groupproject.models.sales;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Table(name = "sales_order")
@Entity
public class SalesOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private LocalDate orderDate;
    @Column(nullable = false)
    private LocalDate shippingDate;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private SalesOrderStatus status;
    @Column(nullable = false)
    private double totalCost;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public SalesOrder setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public SalesOrder setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    public SalesOrder setShippingDate(LocalDate shippingDate) {
        this.shippingDate = shippingDate;
        return this;
    }

    public SalesOrderStatus getStatus() {
        return status;
    }

    public SalesOrder setStatus(SalesOrderStatus status) {
        this.status = status;
        return this;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public SalesOrder setTotalCost(double totalCost) {
        this.totalCost = totalCost;
        return this;
    }
}