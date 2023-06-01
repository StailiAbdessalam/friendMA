package com.Abdessalam.friendMA.service;

import com.Abdessalam.friendMA.dto.model.ProductDto;
import com.Abdessalam.friendMA.entity.Product;
import com.Abdessalam.friendMA.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Page<Product> getProducts(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAllProducts(pageable);
    }

    public Page<Product> getProductsByCity(int page, int size, String city) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAllProductsByCity(pageable, city);
    }
}
