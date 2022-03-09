package com.example.demo.controller;

import com.example.demo.dto.CommonResponse;
import com.example.demo.dto.ProductDto;
import com.example.demo.dto.ProductUpdateStockDto;
import com.example.demo.entity.ProductEntity;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

//    @GetMapping("")
//    public List<ProductEntity> getProducts(@RequestParam(value = "inStock", defaultValue = "0") boolean isInStock) {
//        return productService.fetch(isInStock);
//    }

    @GetMapping("")
    public List<ProductEntity> getProducts(@RequestParam(value = "maxPrice", defaultValue = "0") long isInStock) {
        return productService.fetch(isInStock);
    }

    @GetMapping("{id}")
    public ProductEntity getProduct(@PathVariable("id") String id) {
        return productService.findById(Long.parseLong(id));
    }

    @PostMapping("")
    public ProductEntity addProduct(@RequestBody ProductDto productDto) {
        return productService.add(productDto);
    }

    @PutMapping("/stock")
    public ProductEntity updateStock(@RequestBody ProductUpdateStockDto productDto) {
        return productService.updateStock(productDto);
    }

    @DeleteMapping("{id}")
    public CommonResponse deleteProduct(@PathVariable("id") String id) {
        productService.deleteById(Long.parseLong(id));
        return new CommonResponse("Successfully delete product");
    }
}
