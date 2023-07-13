package com.example.demosecurityjwt.service;

import com.example.demosecurityjwt.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProductService {
    Product saveProduct(Product product);

    Product getProductById(Long id);

    void deleteById(Long id);

    void updateProduct(Long id, Product product);

    Page<Product> getAll(String name, Pageable pageable);

    List<Product> getAll();
}
