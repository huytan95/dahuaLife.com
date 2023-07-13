package com.example.demosecurityjwt.repository;

import com.example.demosecurityjwt.model.Categories;
import com.example.demosecurityjwt.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IProductRepo extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long id);
    Page<Product> findAllByNameContaining(String name, Pageable pageable);
    List<Product> findAllByCategories(Categories categories);
}
