package com.example.demo.service;

import com.example.demo.dto.ProductDto;
import com.example.demo.dto.ProductUpdateStockDto;
import com.example.demo.entity.ProductEntity;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public ProductEntity add(ProductDto request) {
        ProductEntity product = new ProductEntity(
                request.getProductName(),
                request.getStock(),
                request.getPrice()
        );
        return productRepository.save(product);
    }

//    public List<ProductEntity> fetch(boolean isInStock) {
//        if(isInStock){
//            return productRepository.findByStockGreaterThan(0);
//        }
//        return productRepository.findAll();
//    }

    public List<ProductEntity> fetch(long maxPrice) {
        if(maxPrice>0){
            return productRepository.findByPriceLessThanEqual(maxPrice);
        }
        return productRepository.findAll();
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    public ProductEntity findById(Long id) {
        return productRepository.findById(id).orElse(new ProductEntity());
    }

    public ProductEntity updateStock(ProductUpdateStockDto productDto) {
        ProductEntity product = findById(productDto.getId());
        product.setStock(product.getStock() + productDto.getNumberOfStock());
        return productRepository.save(product);
    }
}
