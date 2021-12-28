package com.example.groupproject.service;

import com.example.groupproject.dto.PurchaseOrderProductDto;
import com.example.groupproject.dto.SalesOrderProductDto;
import com.example.groupproject.models.PurchaseOrderProduct;
import com.example.groupproject.models.sales.SalesOrderProduct;

import java.util.List;

public interface SalesOrderProductService {

    SalesOrderProduct create(SalesOrderProductDto salesOrderProductDto);

    SalesOrderProduct update(SalesOrderProductDto salesOrderProductDto);

    void deleteById(long id);

    List<SalesOrderProduct> getAllByPurchaseOrderId(long salesOrderId);
}
