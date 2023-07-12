package com.example.demosecurityjwt.service;

import com.example.demosecurityjwt.model.Categories;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICategoriesService{
    List<Categories> getAll();

    Categories getById(Long id);

    Categories addCategories(Categories categories);

    void disableCategories(Long id);

    Categories updateCategories(Long id, Categories categories);
}
