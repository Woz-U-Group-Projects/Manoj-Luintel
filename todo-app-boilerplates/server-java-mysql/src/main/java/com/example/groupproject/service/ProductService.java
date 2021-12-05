package com.example.groupproject.service;

import com.example.groupproject.dto.ProductDto;
import com.example.groupproject.models.Product;

import java.util.List;

public interface ProductService {

    Product save(ProductDto productDto);

    Product getById(Long id);

    List<Product> getAll();

    void deleteById (Long id);

    Product update(ProductDto productDto);
}
