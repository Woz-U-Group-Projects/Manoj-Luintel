package com.example.groupproject.controllers.sales;

import com.example.groupproject.dto.SalesOrderProductDto;
import com.example.groupproject.service.SalesOrderProductService;
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
public class SalesOrderProductController {

    private final SalesOrderProductService salesOrderProductService;

    public SalesOrderProductController(SalesOrderProductService salesOrderProductService) {
        this.salesOrderProductService = salesOrderProductService;
    }

    @PostMapping("/sales-order-product")
    public ResponseEntity<?> save(@RequestBody @Validated SalesOrderProductDto dto){
        return LocalResponse.configure(salesOrderProductService.create(dto));
    }

    @PutMapping("/sales-order-product")
    public ResponseEntity<?> update(@RequestBody @Validated SalesOrderProductDto dto){
        return LocalResponse.configure(salesOrderProductService.update(dto));
    }

    @GetMapping("/sales-order-products")
    public ResponseEntity<?> get(long purchaseOrderId){
        return LocalResponse.configure(salesOrderProductService.getAllByPurchaseOrderId(purchaseOrderId));
    }


    @DeleteMapping("/sales-order-product")
    public ResponseEntity<?> delete(@RequestParam long id){
        salesOrderProductService.deleteById(id);
        return LocalResponse.configure(String.format("SalesOrderProduct with id %d has been deleted successfully",id));
    }
}
