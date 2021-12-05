package com.example.groupproject.controllers;

import com.example.groupproject.dto.ProductDto;
import com.example.groupproject.models.Product;
import com.example.groupproject.service.ProductService;
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

import java.util.Optional;

@RestController
@RequestMapping("/inv")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/product")
    public ResponseEntity<?> save(@RequestBody @Validated ProductDto productDto){
       return LocalResponse.configure(productService.save(productDto));
    }

    @PutMapping("/product")
    public ResponseEntity<?> update(@RequestBody @Validated ProductDto productDto){
        return LocalResponse.configure(productService.update(productDto));
    }

    @GetMapping("/products")
    public ResponseEntity<?> getProducts(@RequestParam(required = false)Optional<Long> id){
        if (id.isPresent()){
            return LocalResponse.configure(productService.getById(id.get()));
        }else {
            return LocalResponse.configure(productService.getAll());
        }
    }

    @DeleteMapping("/product")
    public ResponseEntity<?> delete(@RequestParam long id){
        productService.deleteById(id);
        return LocalResponse.configure(String.format("Product with id %d has been deleted successully.",id));
    }


    }
