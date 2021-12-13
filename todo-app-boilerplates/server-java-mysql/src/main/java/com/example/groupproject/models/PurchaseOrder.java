package com.example.groupproject.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Table(name = "purchase_order")
@Entity
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private LocalDate orderDate;
    @Column(nullable = false)
    private LocalDate receivedDate;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PurchaseOrderStatus status;
    /*@OneToMany(mappedBy = "purchaseOrder")
    private List<PurchaseOrderProduct> purchaseOrderProducts = new ArrayList<>();*/



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public PurchaseOrder setName(String name) {
        this.name = name;
        return this;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public PurchaseOrder setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
        return this;
    }

    public LocalDate getReceivedDate() {
        return receivedDate;
    }

    public PurchaseOrder setReceivedDate(LocalDate receivedDate) {
        this.receivedDate = receivedDate;
        return this;
    }

    public PurchaseOrderStatus getStatus() {
        return status;
    }

    public PurchaseOrder setStatus(PurchaseOrderStatus status) {
        this.status = status;
        return this;
    }

  /*  public List<PurchaseOrderProduct> getPurchaseOrderProducts() {
        return purchaseOrderProducts;
    }

    public PurchaseOrder setPurchaseOrderProducts(List<PurchaseOrderProduct> purchaseOrderProducts) {
        this.purchaseOrderProducts = purchaseOrderProducts;
        return this;
    }*/
}