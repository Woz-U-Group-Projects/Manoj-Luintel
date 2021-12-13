package com.example.groupproject.repository;

import com.example.groupproject.models.PurchaseOrder;
import com.example.groupproject.models.PurchaseOrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseOrderProductRepository extends JpaRepository<PurchaseOrderProduct, Long> {
    List<PurchaseOrderProduct> findAllByPurchaseOrder(PurchaseOrder purchaseOrder);
}