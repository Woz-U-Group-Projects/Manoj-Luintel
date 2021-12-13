package com.example.groupproject.service.impl;

import com.example.groupproject.Exception.InvNotFoundException;
import com.example.groupproject.dto.PurchaseOrderProductDto;
import com.example.groupproject.models.Product;
import com.example.groupproject.models.PurchaseOrder;
import com.example.groupproject.models.PurchaseOrderProduct;
import com.example.groupproject.repository.PurchaseOrderProductRepository;
import com.example.groupproject.service.ProductService;
import com.example.groupproject.service.PurchaseOrderProductService;
import com.example.groupproject.service.PurchaseOrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseOrderProductServiceImpl implements PurchaseOrderProductService {
    private final PurchaseOrderProductRepository purchaseOrderProductRepository;
    private final ProductService productService;
    private final PurchaseOrderService purchaseOrderService;

    public PurchaseOrderProductServiceImpl(PurchaseOrderProductRepository purchaseOrderProductRepository,
                                           ProductService productService,
                                           PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderProductRepository = purchaseOrderProductRepository;
        this.productService = productService;
        this.purchaseOrderService = purchaseOrderService;
    }

    @Override
    public PurchaseOrderProduct create(PurchaseOrderProductDto purchaseOrderProductDto) {
        Product product = productService.getById(purchaseOrderProductDto.getProductId());
        PurchaseOrder purchaseOrder = purchaseOrderService.getById(purchaseOrderProductDto.getPurchaseOrderId());

        PurchaseOrderProduct purchaseOrderProduct = new PurchaseOrderProduct()
                .setProduct(product)
                .setPurchaseOrder(purchaseOrder)
                .setUnits(purchaseOrderProductDto.getUnits());
        return purchaseOrderProductRepository.save(purchaseOrderProduct);
    }

    @Override
    public PurchaseOrderProduct update(PurchaseOrderProductDto purchaseOrderProductDto) {
        PurchaseOrderProduct purchaseOrderProduct = this.getById(purchaseOrderProductDto.getId());
        purchaseOrderProduct.setUnits(purchaseOrderProduct.getUnits());
        return purchaseOrderProductRepository.save(purchaseOrderProduct);
    }

    @Override
    public void deleteById(long id) {
        purchaseOrderProductRepository.delete(getById(id));
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
