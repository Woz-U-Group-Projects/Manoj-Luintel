package com.example.groupproject.models;

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

@Table(name = "purchase_order_product")
@Entity
public class PurchaseOrderProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @JsonIgnoreProperties({"product","purchaseOrder"})
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;
    @JsonIgnoreProperties({"product","purchaseOrder"})
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "purchaser_order_id")
    private PurchaseOrder purchaseOrder;
    private int units;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public PurchaseOrderProduct setProduct(Product product) {
        this.product = product;
        return this;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public PurchaseOrderProduct setPurchaseOrder(PurchaseOrder purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
        return this;
    }

    public int getUnits() {
        return units;
    }

    public PurchaseOrderProduct setUnits(int units) {
        this.units = units;
        return this;
    }
}