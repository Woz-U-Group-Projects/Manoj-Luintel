package com.example.groupproject.controllers.sales;

import com.example.groupproject.dto.PurchaseOrderDto;
import com.example.groupproject.dto.SalesOrderDto;
import com.example.groupproject.models.PurchaseOrderStatus;
import com.example.groupproject.service.SalesOrderService;
import com.example.groupproject.utils.LocalResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
public class SalesOrderController {

    private final SalesOrderService salesOrderService;

    public SalesOrderController(SalesOrderService salesOrderService) {
        this.salesOrderService = salesOrderService;
    }

    @PostMapping("/sales-order")
    public ResponseEntity<?> save(@RequestBody @Valid SalesOrderDto salesOrderDto){
        return LocalResponse.configure(salesOrderService.create(salesOrderDto));
    }

    @PutMapping("/sales-order")
    public ResponseEntity<?> update(@RequestBody @Validated SalesOrderDto salesOrderDto){
        return LocalResponse.configure(salesOrderService.update(salesOrderDto));
    }

    @GetMapping("/sales-orders")
    public ResponseEntity<?> get(@RequestParam(required = false) Optional<Long> id){
        if (id.isPresent()){
            return LocalResponse.configure(salesOrderService.getModelById(id.get()));
        }else{
            return LocalResponse.configure(salesOrderService.getAllModels());

        }
    }

    @PutMapping("/sales-order-complete")
    public ResponseEntity<?> completeOrder(@RequestParam int id){
        return LocalResponse.configure(salesOrderService.completeOrder(id));
    }

    @PutMapping("/sales-order-cancel")
    public ResponseEntity<?> cancelOrder(@RequestParam int id){
        return LocalResponse.configure(salesOrderService.cancelOrder(id));
    }

}
