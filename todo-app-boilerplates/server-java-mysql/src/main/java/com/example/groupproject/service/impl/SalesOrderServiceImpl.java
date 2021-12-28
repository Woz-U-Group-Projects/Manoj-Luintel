package com.example.groupproject.service.impl;

import com.example.groupproject.Exception.InvNotFoundException;
import com.example.groupproject.dto.SalesOrderDto;
import com.example.groupproject.models.Product;
import com.example.groupproject.models.PurchaseOrder;
import com.example.groupproject.models.PurchaseOrderStatus;
import com.example.groupproject.models.sales.SalesOrder;
import com.example.groupproject.models.sales.SalesOrderModel;
import com.example.groupproject.models.sales.SalesOrderProduct;
import com.example.groupproject.models.sales.SalesOrderStatus;
import com.example.groupproject.repository.product.ProductRepository;
import com.example.groupproject.repository.sales.SalesOrderProductRepository;
import com.example.groupproject.repository.sales.SalesOrderRepository;
import com.example.groupproject.service.SalesOrderService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SalesOrderServiceImpl implements SalesOrderService {

    private final SalesOrderRepository salesOrderRepository;
    private final SalesOrderProductRepository salesOrderProductRepository;
    private final ProductRepository productRepository;

    public SalesOrderServiceImpl(SalesOrderRepository salesOrderRepository,
                                 SalesOrderProductRepository salesOrderProductRepository,
                                 ProductRepository productRepository) {
        this.salesOrderRepository = salesOrderRepository;
        this.salesOrderProductRepository = salesOrderProductRepository;
        this.productRepository = productRepository;
    }

    @Override
    public SalesOrderModel create(SalesOrderDto salesOrderDto) {
        Assert.isTrue(salesOrderDto.getShippingDate().isAfter(salesOrderDto.getOrderDate()),"shippingDate cannot be earlier than order data");
        SalesOrder salesOrder = dtoToEntity(salesOrderDto);
        salesOrder.setStatus(SalesOrderStatus.Created);
        return entityToModel(salesOrderRepository.save(salesOrder));
    }

    @Override
    public SalesOrder save(SalesOrder salesOrder) {
        return salesOrderRepository.save(salesOrder);
    }

    @Override
    public SalesOrderModel update(SalesOrderDto salesOrderDto) {
        SalesOrder salesOrder = getById(salesOrderDto.getId());
        Assert.isTrue(salesOrderDto.getShippingDate().isAfter(salesOrderDto.getOrderDate()),"shippingDate cannot be earlier than order data");
        Assert.isTrue(salesOrder.getStatus().equals(SalesOrderStatus.Created),"Could only update if status is created");
        salesOrder.setName(salesOrderDto.getName())
                .setOrderDate(salesOrderDto.getOrderDate())
                .setShippingDate(salesOrderDto.getShippingDate());
        return entityToModel(salesOrderRepository.save(salesOrder));
    }

    @Override
    public SalesOrder getById(long id) {
        return salesOrderRepository.findById(id)
                .orElseThrow(() -> new InvNotFoundException(String.format("SalesOrder not found for id %d",id)));
    }

    @Override
    public SalesOrderModel getModelById(long id) {
        return entityToModel(getById(id));
    }

    @Override
    public List<SalesOrderModel> getAllModels() {
        return salesOrderRepository.findAll()
                .stream()
                .map(this::entityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public SalesOrderModel completeOrder(int id) {
        SalesOrder salesOrder = getById(id);

        Assert.isTrue(salesOrder.getStatus().equals(SalesOrderStatus.Created),"Cannot complete order. Status must be created.");
        recalculateStock(salesOrder);
        salesOrder.setStatus(SalesOrderStatus.Completed);
        return entityToModel(salesOrderRepository.save(salesOrder));
    }

    @Override
    public SalesOrderModel cancelOrder(int id) {
        SalesOrder salesOrder = getById(id);
        Assert.isTrue(salesOrder.getStatus().equals(SalesOrderStatus.Created),"Cannot Cancel order. Status must be created.");
        salesOrder.setStatus(SalesOrderStatus.Cancelled);
        return entityToModel(salesOrderRepository.save(salesOrder));
    }

    private void recalculateStock(SalesOrder salesOrder) {
        List<SalesOrderProduct> salesOrderProducts = salesOrderProductRepository.findAllBySalesOrder(salesOrder);
        salesOrderProducts.forEach(salesOrderProduct -> {
            Optional<Product> product = productRepository.findById(salesOrderProduct.getProduct().getId());
            if (product.isPresent()){
                Product temp = product.get();
                temp.setUnitsInStock(temp.getUnitsInStock() - salesOrderProduct.getUnits());
                productRepository.save(temp);
            }
        });
    }
    private SalesOrder dtoToEntity(SalesOrderDto dto){
        return new SalesOrder()
                .setName(dto.getName())
                .setOrderDate(dto.getOrderDate())
                .setShippingDate(dto.getShippingDate());
    }

    private SalesOrderModel entityToModel(SalesOrder salesOrder) {
        return new SalesOrderModel()
                .setName(salesOrder.getName())
                .setId(salesOrder.getId())
                .setStatus(salesOrder.getStatus())
                .setOrderDate(salesOrder.getOrderDate())
                .setShippingDate(salesOrder.getShippingDate())
                .setTotalCost(salesOrder.getTotalCost());
    }

}
