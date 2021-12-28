package com.example.groupproject.service;

import com.example.groupproject.dto.PurchaseOrderDto;
import com.example.groupproject.dto.SalesOrderDto;
import com.example.groupproject.models.PurchaseOrder;
import com.example.groupproject.models.PurchaseOrderModel;
import com.example.groupproject.models.PurchaseOrderStatus;
import com.example.groupproject.models.sales.SalesOrder;
import com.example.groupproject.models.sales.SalesOrderModel;

import java.util.List;

public interface SalesOrderService {

    SalesOrderModel create(SalesOrderDto salesOrderDto);

    SalesOrderModel update(SalesOrderDto salesOrderDto);

    SalesOrder getById(long id);

    SalesOrderModel getModelById(long id);

    List<SalesOrderModel> getAllModels();

    SalesOrderModel completeOrder(int id);

    SalesOrderModel cancelOrder(int id);

    SalesOrder save(SalesOrder salesOrder);
}
