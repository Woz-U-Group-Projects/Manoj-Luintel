package com.example.groupproject.service.impl;

import com.example.groupproject.Exception.InvNotFoundException;
import com.example.groupproject.dto.SalesOrderProductDto;
import com.example.groupproject.models.Product;
import com.example.groupproject.models.PurchaseOrder;
import com.example.groupproject.models.sales.SalesOrder;
import com.example.groupproject.models.sales.SalesOrderProduct;
import com.example.groupproject.repository.sales.SalesOrderProductRepository;
import com.example.groupproject.service.ProductService;
import com.example.groupproject.service.SalesOrderProductService;
import com.example.groupproject.service.SalesOrderService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class SalesOrderProductServiceImpl implements SalesOrderProductService {

    private final SalesOrderProductRepository salesOrderProductRepository;
    private final ProductService productService;
    private final SalesOrderService salesOrderService;

    public SalesOrderProductServiceImpl(SalesOrderProductRepository salesOrderProductRepository, ProductService productService, SalesOrderService salesOrderService) {
        this.salesOrderProductRepository = salesOrderProductRepository;
        this.productService = productService;
        this.salesOrderService = salesOrderService;
    }

    @Override
    public SalesOrderProduct create(SalesOrderProductDto salesOrderProductDto) {
        Product product = productService.getById(salesOrderProductDto.getProductId());
        Assert.isTrue(product.getUnitsInStock() >= salesOrderProductDto.getUnits(),"Insufficient product stock.");
        SalesOrder salesOrder = salesOrderService.getById(salesOrderProductDto.getSalesOrderId());

        SalesOrderProduct salesOrderProduct = new SalesOrderProduct()
                .setProduct(product)
                .setSalesOrder(salesOrder)
                .setUnits(salesOrderProductDto.getUnits())
                .setCost(product.getSellingPrice());
        salesOrder.setTotalCost(salesOrder.getTotalCost()+ salesOrderProduct.getCost());
        salesOrderService.save(salesOrder);
        return salesOrderProductRepository.save(salesOrderProduct);
    }

    @Override
    public SalesOrderProduct update(SalesOrderProductDto salesOrderProductDto) {
        SalesOrderProduct salesOrderProduct = this.getById(salesOrderProductDto.getId());
        Assert.isTrue(salesOrderProduct.getProduct().getUnitsInStock() >= salesOrderProductDto.getUnits(),"Insufficient product stock.");
        salesOrderProduct.setUnits(salesOrderProduct.getUnits());
        recalculateTotalCost(salesOrderProduct.getSalesOrder());
        return salesOrderProductRepository.save(salesOrderProduct);
    }

    @Override
    public void deleteById(long id) {
        SalesOrderProduct salesOrderProduct = getById(id);
        salesOrderProductRepository.delete(salesOrderProduct);
        recalculateTotalCost(salesOrderProduct.getSalesOrder());
    }

    @Override
    public List<SalesOrderProduct> getAllByPurchaseOrderId(long salesOrderId) {
        SalesOrder salesOrder = salesOrderService.getById(salesOrderId);
        return salesOrderProductRepository.findAllBySalesOrder(salesOrder);
    }


    public SalesOrderProduct getById(long id){
        return salesOrderProductRepository.findById(id)
                .orElseThrow(()-> new InvNotFoundException(String.format("SalesOrderProduct not found with id %d",id)));
    }

    private void recalculateTotalCost(SalesOrder salesOrder){
        List<SalesOrderProduct> salesOrderProducts = salesOrderProductRepository.findAllBySalesOrder(salesOrder);
        AtomicReference<Double> totalCost = new AtomicReference<>(0.0);
        salesOrderProducts.stream()
                .forEach(salesOrderProduct -> {
                    totalCost.updateAndGet(v -> v + salesOrderProduct.getCost());
                });
        salesOrder.setTotalCost(totalCost.get());
        salesOrderService.save(salesOrder);

    }
}
