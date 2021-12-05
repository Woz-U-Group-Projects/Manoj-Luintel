package com.example.groupproject.service.impl;

import com.example.groupproject.Exception.InvNotFoundException;
import com.example.groupproject.dto.ProductDto;
import com.example.groupproject.models.Product;
import com.example.groupproject.repository.product.ProductRepository;
import com.example.groupproject.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(ProductDto productDto) {
        return productRepository.save(dtoToEntity(productDto));
    }

    @Override
    public Product update(ProductDto productDto) {
        Product product = getById(productDto.getId());
        return productRepository.save(dtoToEntity(productDto).setId(product.getId()));
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(()-> new InvNotFoundException(String.format("Product with id %d not found",id)));
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        productRepository.delete(this.getById(id));
    }


    private Product dtoToEntity(ProductDto productDto) {
        return  new Product()
                .setName(productDto.getName())
                .setCode(productDto.getCode())
                .setDescription(productDto.getDescription())
                .setCostPrice(productDto.getCostPrice())
                .setSellingPrice(productDto.getSellingPrice())
                .setLowStockThreshold(productDto.getLowStockThreshold())
                .setPurchaseDate(productDto.getPurchaseDate())
                .setUnitsInStock(productDto.getUnitsInStock())
                .setUnitOfMeasure(productDto.getUnitOfMeasure());
    }
}
