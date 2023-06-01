package com.Abdessalam.friendMA.controller;

import com.Abdessalam.friendMA.dto.model.ProductDto;
import com.Abdessalam.friendMA.entity.Product;
import com.Abdessalam.friendMA.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public Page<Product> getProducts(@RequestParam(defaultValue = "0") int page , @RequestParam(defaultValue = "10") int size){
        return productService.getProducts(page, size);
    }

    @GetMapping("/city/{city}")
    public Page<Product> getProductsByCity(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @PathVariable String city){
        return productService.getProductsByCity(page, size, city);
    }



}
