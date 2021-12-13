package com.example.groupproject.service;

import com.example.groupproject.dto.PurchaseOrderProductDto;
import com.example.groupproject.models.PurchaseOrderProduct;

import java.util.List;

public interface PurchaseOrderProductService {

    PurchaseOrderProduct create(PurchaseOrderProductDto purchaseOrderProductDto);

    PurchaseOrderProduct update(PurchaseOrderProductDto purchaseOrderProductDto);

    void deleteById(long id);

    List<PurchaseOrderProduct> getAllByPurchaseOrderId(long purchaseOrderId);
}
