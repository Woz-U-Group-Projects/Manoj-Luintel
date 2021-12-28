package com.example.groupproject.service;

import com.example.groupproject.dto.PurchaseOrderDto;
import com.example.groupproject.dto.PurchaseOrderProductDto;
import com.example.groupproject.models.PurchaseOrder;
import com.example.groupproject.models.PurchaseOrderModel;
import com.example.groupproject.models.PurchaseOrderProduct;
import com.example.groupproject.models.PurchaseOrderStatus;

import java.util.List;

public interface PurchaseOrderService {
    PurchaseOrderModel create(PurchaseOrderDto purchaseOrderDto);

    PurchaseOrderModel update(PurchaseOrderDto purchaseOrderDto);

    PurchaseOrder getById(long id);

    PurchaseOrderModel getModelById(long id);

    List<PurchaseOrderModel> getAllModels();

    PurchaseOrderModel changeStatus(long id,PurchaseOrderStatus status);


    PurchaseOrderModel completeOrder(int id);

    PurchaseOrderModel cancelOrder(int id);
}
