package com.example.groupproject.controllers.purchaseorder;

import com.example.groupproject.dto.PurchaseOrderDto;
import com.example.groupproject.dto.PurchaseOrderProductDto;
import com.example.groupproject.service.PurchaseOrderProductService;
import com.example.groupproject.service.PurchaseOrderService;
import com.example.groupproject.utils.LocalResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inv")
public class PurchaseOrderProductController {
    private final PurchaseOrderProductService purchaseOrderProductService;
    private final PurchaseOrderService purchaseOrderService;

    public PurchaseOrderProductController(PurchaseOrderProductService purchaseOrderProductService, PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderProductService = purchaseOrderProductService;
        this.purchaseOrderService = purchaseOrderService;
    }

    @PostMapping("/purchase-order-product")
    public ResponseEntity<?> save(@RequestBody @Validated PurchaseOrderProductDto dto){
        return LocalResponse.configure(purchaseOrderProductService.create(dto));
    }

    @PutMapping("/purchase-order-product")
    public ResponseEntity<?> update(@RequestBody @Validated PurchaseOrderProductDto dto){
        return LocalResponse.configure(purchaseOrderProductService.update(dto));
    }

    @GetMapping("/purchase-order-products")
    public ResponseEntity<?> get(long purchaseOrderId){
            return LocalResponse.configure(purchaseOrderProductService.getAllByPurchaseOrderId(purchaseOrderId));
    }


    @DeleteMapping("/purchase-order-product")
    public ResponseEntity<?> delete(@RequestParam long id){
        purchaseOrderProductService.deleteById(id);
        return LocalResponse.configure(String.format("PurchaseOrderProduct with id %d has been deleted successfully",id));
    }

}
