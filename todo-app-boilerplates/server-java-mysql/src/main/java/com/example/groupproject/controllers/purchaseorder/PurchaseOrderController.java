package com.example.groupproject.controllers.purchaseorder;

import com.example.groupproject.dto.ProductDto;
import com.example.groupproject.dto.PurchaseOrderDto;
import com.example.groupproject.models.PurchaseOrderStatus;
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

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/inv")
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    public PurchaseOrderController(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    @PostMapping("/purchase-order")
    public ResponseEntity<?> save(@RequestBody @Valid PurchaseOrderDto purchaseOrderDto){
        return LocalResponse.configure(purchaseOrderService.create(purchaseOrderDto));
    }

    @PutMapping("/purchase-order")
    public ResponseEntity<?> update(@RequestBody @Validated PurchaseOrderDto purchaseOrderDto){
        return LocalResponse.configure(purchaseOrderService.update(purchaseOrderDto));
    }

    @GetMapping("/purchase-orders")
    public ResponseEntity<?> get(@RequestParam(required = false) Optional<Long> id){
        if (id.isPresent()){
            return LocalResponse.configure(purchaseOrderService.getModelById(id.get()));
        }else{
            return LocalResponse.configure(purchaseOrderService.getAllModels());

        }
    }


    @PostMapping("/purchase-order/status")
    public ResponseEntity<?> changeStatus(@RequestParam long purchaseOrderId, PurchaseOrderStatus status){
            return LocalResponse.configure(purchaseOrderService.changeStatus(purchaseOrderId,status));
    }

    @PutMapping("/purchase-order-complete")
    public ResponseEntity<?> completeOrder(@RequestParam int id){
        return LocalResponse.configure(purchaseOrderService.completeOrder(id));
    }

    @PutMapping("/purchase-order-cancel")
    public ResponseEntity<?> cancelOrder(@RequestParam int id){
        return LocalResponse.configure(purchaseOrderService.cancelOrder(id));
    }


}
