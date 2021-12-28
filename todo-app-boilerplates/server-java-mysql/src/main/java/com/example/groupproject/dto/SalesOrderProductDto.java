package com.example.groupproject.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class SalesOrderProductDto {

    private long id;
    @NotNull(message = "salesORderId cannot be null")
    private Long salesOrderId;
    @NotNull(message = "productId cannot be null")
    private Long productId;
    @Positive(message = "units must be positive")
    private int units;

    public long getId() {
        return id;
    }

    public SalesOrderProductDto setId(long id) {
        this.id = id;
        return this;
    }

    public Long getSalesOrderId() {
        return salesOrderId;
    }

    public SalesOrderProductDto setSalesOrderId(Long salesOrderId) {
        this.salesOrderId = salesOrderId;
        return this;
    }

    public Long getProductId() {
        return productId;
    }

    public SalesOrderProductDto setProductId(Long productId) {
        this.productId = productId;
        return this;
    }

    public int getUnits() {
        return units;
    }

    public SalesOrderProductDto setUnits(int units) {
        this.units = units;
        return this;
    }
}
