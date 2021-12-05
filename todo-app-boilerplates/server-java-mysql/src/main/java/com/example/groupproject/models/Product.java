package com.example.groupproject.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Table(name = "product")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
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
    private boolean batchable = false;
    private String code;

    public Long getId() {
        return id;
    }

    public Product setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public Double getCostPrice() {
        return costPrice;
    }

    public Product setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
        return this;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public Product setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Product setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    public Product setUnitOfMeasure(String unitOfMeasure) {
        this.unitOfMeasure = unitOfMeasure;
        return this;
    }

    public Integer getUnitsInStock() {
        return unitsInStock;
    }

    public Product setUnitsInStock(Integer unitsInStock) {
        this.unitsInStock = unitsInStock;
        return this;
    }

    public Integer getInBasket() {
        return inBasket;
    }

    public Product setInBasket(Integer inBasket) {
        this.inBasket = inBasket;
        return this;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public Product setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
        return this;
    }

    public Integer getLowStockThreshold() {
        return lowStockThreshold;
    }

    public Product setLowStockThreshold(Integer lowStockThreshold) {
        this.lowStockThreshold = lowStockThreshold;
        return this;
    }

    public boolean isBatchable() {
        return batchable;
    }

    public Product setBatchable(boolean batchable) {
        this.batchable = batchable;
        return this;
    }

    public String getCode() {
        return code;
    }

    public Product setCode(String code) {
        this.code = code;
        return this;
    }
}