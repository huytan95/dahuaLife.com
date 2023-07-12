package com.example.demosecurityjwt.repository;

import com.example.demosecurityjwt.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategoriesRepo extends JpaRepository<Categories, Long> {
}
