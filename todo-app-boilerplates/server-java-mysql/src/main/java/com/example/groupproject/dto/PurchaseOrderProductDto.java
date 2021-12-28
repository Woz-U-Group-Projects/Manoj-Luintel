package com.example.groupproject.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class PurchaseOrderProductDto {
    private long id;
    @NotNull(message = "purchaseOrderId cannot be null")
    private Long purchaseOrderId;
    @NotNull(message = "productId cannot be null")
    private Long productId;
    @Positive(message = "units must be positive")
    private int units;
    @Positive(message = "cost must be positive")
    private int cost;

    public long getId() {
        return id;
    }

    public PurchaseOrderProductDto setId(long id) {
        this.id = id;
        return this;
    }

    public Long getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public PurchaseOrderProductDto setPurchaseOrderId(Long purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
        return this;
    }

    public Long getProductId() {
        return productId;
    }

    public PurchaseOrderProductDto setProductId(Long productId) {
        this.productId = productId;
        return this;
    }

    public int getUnits() {
        return units;
    }

    public PurchaseOrderProductDto setUnits(int units) {
        this.units = units;
        return this;
    }

    public int getCost() {
        return cost;
    }

    public PurchaseOrderProductDto setCost(int cost) {
        this.cost = cost;
        return this;
    }
}
