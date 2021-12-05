package com.example.groupproject.dto;

import java.time.LocalDate;

public class ProductDto {
    private Long id;
    private String name;
    private Double costPrice;
    private Double sellingPrice;
    private String description;
    private String unitOfMeasure;
    private Integer unitsInStock;
    private Integer inBasket;
    private LocalDate purchaseDate;
    private Integer lowStockThreshold;
    private boolean batchable;
    private String code;

    public Long getId() {
        return id;
    }

    public ProductDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductDto setName(String name) {
        this.name = name;
        return this;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public ProductDto setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
        return this;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public ProductDto setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductDto setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public ProductDto setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
        return this;
    }

    public Integer getUnitsInStock() {
        return unitsInStock;
    }

    public ProductDto setUnitsInStock(Integer unitsInStock) {
        this.unitsInStock = unitsInStock;
        return this;
    }

    public Integer getInBasket() {
        return inBasket;
    }

    public ProductDto setInBasket(Integer inBasket) {
        this.inBasket = inBasket;
        return this;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public ProductDto setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
        return this;
    }

    public Integer getLowStockThreshold() {
        return lowStockThreshold;
    }

    public ProductDto setLowStockThreshold(Integer lowStockThreshold) {
        this.lowStockThreshold = lowStockThreshold;
        return this;
    }

    public boolean isBatchable() {
        return batchable;
    }

    public ProductDto setBatchable(boolean batchable) {
        this.batchable = batchable;
        return this;
    }

    public String getCode() {
        return code;
    }

    public ProductDto setCode(String code) {
        this.code = code;
        return this;
    }
}
