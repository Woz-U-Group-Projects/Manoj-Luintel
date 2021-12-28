package com.example.groupproject.service.impl;

import com.example.groupproject.Exception.BadRequestException;
import com.example.groupproject.Exception.InvNotFoundException;
import com.example.groupproject.dto.PurchaseOrderDto;
import com.example.groupproject.models.Product;
import com.example.groupproject.models.PurchaseOrder;
import com.example.groupproject.models.PurchaseOrderModel;
import com.example.groupproject.models.PurchaseOrderProduct;
import com.example.groupproject.models.PurchaseOrderStatus;
import com.example.groupproject.repository.purchase.PurchaseOrderProductRepository;
import com.example.groupproject.repository.purchase.PurchaseOrderRepository;
import com.example.groupproject.repository.product.ProductRepository;
import com.example.groupproject.service.PurchaseOrderService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final ProductRepository productRepository;
    private final PurchaseOrderProductRepository purchaseOrderProductRepository;

    public PurchaseOrderServiceImpl(PurchaseOrderRepository purchaseOrderRepository, ProductRepository productRepository, PurchaseOrderProductRepository purchaseOrderProductRepository) {
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.productRepository = productRepository;
        this.purchaseOrderProductRepository = purchaseOrderProductRepository;
    }

    @Override
    public PurchaseOrderModel create(PurchaseOrderDto purchaseOrderDto) {
        PurchaseOrder purchaseOrder = dtoToEntity(purchaseOrderDto);
        purchaseOrder.setStatus(PurchaseOrderStatus.Created);
        return entityToModel(purchaseOrderRepository.save(purchaseOrder));
    }

    @Override
    public PurchaseOrderModel update(PurchaseOrderDto purchaseOrderDto) {
        PurchaseOrder purchaseOrder = getById(purchaseOrderDto.getId());
        Assert.isTrue(purchaseOrderDto.getReceivedDate().isAfter(purchaseOrderDto.getOrderDate()),"recievedDate cannot be earlier than order data");
        Assert.isTrue(purchaseOrder.getStatus().equals(PurchaseOrderStatus.Created),"Could only update if status is created");
        purchaseOrder.setName(purchaseOrderDto.getName())
                .setOrderDate(purchaseOrderDto.getOrderDate())
                .setReceivedDate(purchaseOrderDto.getReceivedDate());
        return entityToModel(purchaseOrderRepository.save(purchaseOrder));
    }

    @Override
    public PurchaseOrder getById(long id) {
        return purchaseOrderRepository.findById(id)
                .orElseThrow(() -> new InvNotFoundException(String.format("PurchaseOrder not found for id %d",id)));
    }

    @Override
    public PurchaseOrderModel getModelById(long id) {
        return entityToModel(getById(id));
    }

    @Override
    public List<PurchaseOrderModel> getAllModels() {
        return purchaseOrderRepository.findAll()
                .stream()
                .map(this::entityToModel)
                .collect(Collectors.toList());
    }

    @Override
    public PurchaseOrderModel changeStatus(long id,PurchaseOrderStatus status) {
        PurchaseOrder purchaseOrder = getById(id);
        if (checkUpdatableStatus(status)){
            if (status.equals(PurchaseOrderStatus.Completed)){
                recalculateStock(purchaseOrder);
            }
        }else{
            throw new BadRequestException("Could not update status.");
        }
        purchaseOrder.setStatus(status);
        return entityToModel(purchaseOrderRepository.save(purchaseOrder));
    }

    @Override
    public PurchaseOrderModel completeOrder(int id) {
        PurchaseOrder purchaseOrder = getById(id);

        Assert.isTrue(purchaseOrder.getStatus().equals(PurchaseOrderStatus.Created),"Cannot complete order. Status must be created.");
        recalculateStock(purchaseOrder);
        purchaseOrder.setStatus(PurchaseOrderStatus.Completed);
        return entityToModel(purchaseOrderRepository.save(purchaseOrder));
    }

    @Override
    public PurchaseOrderModel cancelOrder(int id) {
        PurchaseOrder purchaseOrder = getById(id);
        Assert.isTrue(purchaseOrder.getStatus().equals(PurchaseOrderStatus.Created),"Cannot Cancel order. Status must be created.");
        purchaseOrder.setStatus(PurchaseOrderStatus.Cancelled);
        return entityToModel(purchaseOrderRepository.save(purchaseOrder));
    }

    private void recalculateStock(PurchaseOrder purchaseOrder) {
        List<PurchaseOrderProduct> purchaseOrderProducts = purchaseOrderProductRepository.findAllByPurchaseOrder(purchaseOrder);
        purchaseOrderProducts.forEach(purchaseOrderProduct -> {
            Optional<Product> product = productRepository.findById(purchaseOrderProduct.getProduct().getId());
            if (product.isPresent()){
                Product temp = product.get();
                temp.setUnitsInStock(temp.getUnitsInStock()+ purchaseOrderProduct.getUnits());
                productRepository.save(temp);
            }
        });
    }

    private boolean checkUpdatableStatus(PurchaseOrderStatus status) {
        switch (status){
            case Completed:
            case Cancelled:
                return status.equals(PurchaseOrderStatus.Created);
            default:
                return false;
        }
    }

    private PurchaseOrder dtoToEntity(PurchaseOrderDto dto){
        return new PurchaseOrder()
                .setName(dto.getName())
                .setOrderDate(dto.getOrderDate())
                .setReceivedDate(dto.getReceivedDate());
    }

    private PurchaseOrderModel entityToModel(PurchaseOrder purchaseOrder) {
        return new PurchaseOrderModel()
                .setName(purchaseOrder.getName())
                .setId(purchaseOrder.getId())
                .setStatus(purchaseOrder.getStatus())
                .setOrderDate(purchaseOrder.getOrderDate())
                .setReceivedDate(purchaseOrder.getReceivedDate())
                .setTotalCost(purchaseOrder.getTotalCost());
    }


}
