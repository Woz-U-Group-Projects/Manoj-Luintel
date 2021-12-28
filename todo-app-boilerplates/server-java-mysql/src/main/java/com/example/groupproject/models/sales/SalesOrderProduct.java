package com.example.groupproject.models.sales;

import com.example.groupproject.models.Product;
import com.example.groupproject.models.PurchaseOrder;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "sales_order_product")
@Entity
public class SalesOrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @JsonIgnoreProperties({"product","purchaseOrder"})
    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.MERGE})
    @JoinColumn(name = "product_id")
    private Product product;
    @JsonIgnoreProperties({"product","purchaseOrder"})
    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.MERGE})
    @JoinColumn(name = "sales_order_id")
    private SalesOrder salesOrder;
    private int units;
    private Double cost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public SalesOrderProduct setProduct(Product product) {
        this.product = product;
        return this;
    }

    public SalesOrder getSalesOrder() {
        return salesOrder;
    }

    public SalesOrderProduct setSalesOrder(SalesOrder salesOrder) {
        this.salesOrder = salesOrder;
        return this;
    }

    public int getUnits() {
        return units;
    }

    public SalesOrderProduct setUnits(int units) {
        this.units = units;
        return this;
    }

    public Double getCost() {
        return cost;
    }

    public SalesOrderProduct setCost(Double cost) {
        this.cost = cost;
        return this;
    }
}