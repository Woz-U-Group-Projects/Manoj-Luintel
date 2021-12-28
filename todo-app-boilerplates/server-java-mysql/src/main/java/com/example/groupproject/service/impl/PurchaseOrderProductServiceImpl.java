package com.example.groupproject.service.impl;

import com.example.groupproject.Exception.InvNotFoundException;
import com.example.groupproject.dto.PurchaseOrderProductDto;
import com.example.groupproject.models.Product;
import com.example.groupproject.models.PurchaseOrder;
import com.example.groupproject.models.PurchaseOrderProduct;
import com.example.groupproject.repository.purchase.PurchaseOrderProductRepository;
import com.example.groupproject.repository.purchase.PurchaseOrderRepository;
import com.example.groupproject.service.ProductService;
import com.example.groupproject.service.PurchaseOrderProductService;
import com.example.groupproject.service.PurchaseOrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PurchaseOrderProductServiceImpl implements PurchaseOrderProductService {
    private final PurchaseOrderProductRepository purchaseOrderProductRepository;
    private final ProductService productService;
    private final PurchaseOrderService purchaseOrderService;
    private final PurchaseOrderRepository purchaseOrderRepository;

    public PurchaseOrderProductServiceImpl(PurchaseOrderProductRepository purchaseOrderProductRepository,
                                           ProductService productService,
                                           PurchaseOrderService purchaseOrderService, PurchaseOrderRepository purchaseOrderRepository) {
        this.purchaseOrderProductRepository = purchaseOrderProductRepository;
        this.productService = productService;
        this.purchaseOrderService = purchaseOrderService;
        this.purchaseOrderRepository = purchaseOrderRepository;
    }

    @Override
    public PurchaseOrderProduct create(PurchaseOrderProductDto purchaseOrderProductDto) {
        Product product = productService.getById(purchaseOrderProductDto.getProductId());
        PurchaseOrder purchaseOrder = purchaseOrderService.getById(purchaseOrderProductDto.getPurchaseOrderId());

        PurchaseOrderProduct purchaseOrderProduct = new PurchaseOrderProduct()
                .setProduct(product)
                .setPurchaseOrder(purchaseOrder)
                .setUnits(purchaseOrderProductDto.getUnits())
                .setCost(purchaseOrderProductDto.getCost());
        purchaseOrder.setTotalCost(purchaseOrder.getTotalCost()+ purchaseOrderProduct.getCost());
        purchaseOrderRepository.save(purchaseOrder);
        return purchaseOrderProductRepository.save(purchaseOrderProduct);
    }

    @Override
    public PurchaseOrderProduct update(PurchaseOrderProductDto purchaseOrderProductDto) {
        PurchaseOrderProduct purchaseOrderProduct = this.getById(purchaseOrderProductDto.getId());
        purchaseOrderProduct.setUnits(purchaseOrderProduct.getUnits())
                .setCost(purchaseOrderProductDto.getCost());
        recalculateTotalCost(purchaseOrderProduct.getPurchaseOrder());
        return purchaseOrderProductRepository.save(purchaseOrderProduct);
    }

    @Override
    public void deleteById(long id) {
        PurchaseOrderProduct purchaseOrderProduct = getById(id);
        purchaseOrderProductRepository.delete(purchaseOrderProduct);
        recalculateTotalCost(purchaseOrderProduct.getPurchaseOrder());
    }

    private void recalculateTotalCost(PurchaseOrder purchaseOrder){
        List<PurchaseOrderProduct> purchaseOrderProducts = purchaseOrderProductRepository.findAllByPurchaseOrder(purchaseOrder);
        AtomicInteger totalCost = new AtomicInteger();
        purchaseOrderProducts.stream()
                .forEach(purchaseOrderProduct -> {
                    totalCost.addAndGet(purchaseOrderProduct.getCost());
                });
        purchaseOrder.setTotalCost(totalCost.get());
        purchaseOrderRepository.save(purchaseOrder);

    }

    public PurchaseOrderProduct getById(long id){
        return purchaseOrderProductRepository.findById(id)
                .orElseThrow(()-> new InvNotFoundException(String.format("PurchaseOrderProduct not found with id %d",id)));
    }

    @Override
    public List<PurchaseOrderProduct> getAllByPurchaseOrderId(long purchaseOrderId) {
        PurchaseOrder purchaseOrder = purchaseOrderService.getById(purchaseOrderId);
        return purchaseOrderProductRepository.findAllByPurchaseOrder(purchaseOrder);
    }
}
