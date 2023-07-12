package com.example.demosecurityjwt.controller;

import com.example.demosecurityjwt.model.Categories;
import com.example.demosecurityjwt.service.ICategoriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("admin/categories")
@RequiredArgsConstructor
public class CategoriesController {

    private final ICategoriesService iCategoriesService;

    @PostMapping
    public ResponseEntity<Categories> addCategories(@RequestBody Categories categories){
        return ResponseEntity.ok().body(iCategoriesService.addCategories(categories));
    }

    @GetMapping
    public ResponseEntity<List<Categories>> getAllCategories(){
        return ResponseEntity.ok().body
                (
                        iCategoriesService.getAll()
                                .stream()
                                .filter(c -> c.isStatus()==true)
                                .collect(Collectors.toList())
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categories> getCategoriesById(@PathVariable Long id){
        return ResponseEntity.ok().body(iCategoriesService.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> disableCategories(@PathVariable Long id){
        iCategoriesService.disableCategories(id);
        return ResponseEntity.ok("Disable success");
    }

    @PutMapping("{id}")
    public ResponseEntity<Categories> updateCategories(@PathVariable Long id,
                                                       @RequestBody Categories categories){
        return ResponseEntity.ok().body(iCategoriesService.updateCategories(id, categories));
    }
}
