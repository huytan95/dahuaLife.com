package com.example.demosecurityjwt.service.impl;

import com.example.demosecurityjwt.model.Categories;
import com.example.demosecurityjwt.repository.ICategoriesRepo;
import com.example.demosecurityjwt.service.ICategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CategoriesServiceImpl implements ICategoriesService {

    @Autowired
    private ICategoriesRepo iCategoriesRepo;

    @Override
    public List<Categories> getAll(){
        List<Categories> categoriesList = iCategoriesRepo.findAll();

        return categoriesList.stream().filter(Categories::isStatus).collect(Collectors.toList());
    }

    @Override
    public Categories getById(Long id){
        Optional<Categories> categories = iCategoriesRepo.findById(id);
        return categories.orElse(null);
    }

    @Override
    public Categories addCategories(Categories categories){

       return iCategoriesRepo.save(categories);
    }

    @Override
    public void disableCategories(Long id){
        Optional<Categories> optionalCategories = iCategoriesRepo.findById(id);
        if(optionalCategories.isPresent()){
            Categories categories = optionalCategories.get();
            categories.setStatus(false);
            iCategoriesRepo.save(categories);
        }
    }

    @Override
    public Categories updateCategories(Long id, Categories categories){
        Categories categories1 = getById(id);
        categories1.setName(categories.getName());
        categories1.setStatus(categories.isStatus());
        return iCategoriesRepo.save(categories1);
    }
}
