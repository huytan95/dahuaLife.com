package com.example.demosecurityjwt.controller;

import com.example.demosecurityjwt.model.Categories;
import com.example.demosecurityjwt.model.Image;
import com.example.demosecurityjwt.model.Product;
import com.example.demosecurityjwt.service.ICategoriesService;
import com.example.demosecurityjwt.service.IImageService;
import com.example.demosecurityjwt.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class ProductController {

    private final IProductService iProductService;
    private final IImageService iImageService;
    private final ICategoriesService iCategoriesService;

    @PostMapping("admin/product")
    @Transactional
    public ResponseEntity<String> addProduct(@RequestBody Product product,
                                             @RequestParam("imageId") Long imageId,
                                             @RequestParam("cateId") Long cateId) {
        List<Product> products = iProductService.getAll();
        if(products.stream()
                .anyMatch
                        (
                                product1 -> product1.getImageSet().contains(iImageService.getImageById(imageId))
                        )){
            return ResponseEntity.ok("The image with ID " + imageId + " is already associated with a product.");
        }
        Categories categories = iCategoriesService.getById(cateId);
        Image image = iImageService.getImageById(imageId);
        product.getImageSet().add(image);
        product.setCategories(categories);
        image.setProduct(product);
        iProductService.saveProduct(product);
       return ResponseEntity.ok("success");
    }

    @GetMapping("admin/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id){
        return ResponseEntity.ok().body(iProductService.getProductById(id));
    }

    @GetMapping("product")
    public ResponseEntity<Page<Product>> getAllProduct(@RequestParam("pageNumber") int pageNumber,
                                                       @RequestParam("pageSize") int pageSize,
                                                       @RequestParam(value = "keyword", required = false) String keyword){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return ResponseEntity.ok().body(iProductService.getAll(keyword,pageable));
    }

    @GetMapping("product/{cateId}")
    public ResponseEntity<List<Product>> getProductById(@PathVariable Long cateId){
        return ResponseEntity.ok().body(iProductService.getAllByCateId(cateId));
    }
}
