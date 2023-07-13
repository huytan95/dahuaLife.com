package com.example.demosecurityjwt.service.impl;

import com.example.demosecurityjwt.model.Categories;
import com.example.demosecurityjwt.model.Product;
import com.example.demosecurityjwt.repository.IProductRepo;
import com.example.demosecurityjwt.service.ICategoriesService;
import com.example.demosecurityjwt.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final IProductRepo iProductRepo;
    private final ICategoriesService iCategoriesService;


    @Override
    public Product saveProduct(Product product){
        return iProductRepo.save(product);
    }

    @Override
    public Product getProductById(Long id){
        Optional<Product> product = iProductRepo.findById(id);
        return product.orElse(null);
    }

    @Override
    public void deleteById(Long id){

        iProductRepo.deleteById(id);
    }

    @Override
    public void updateProduct(Long productId, Product product) {
        Optional<Product> optionalProduct = iProductRepo.findById(productId);
        if(optionalProduct.isPresent()){
            Product product1 = optionalProduct.get();
            product1.setName(product.getName());
            product1.setDiscount(product.getDiscount());
            product1.setCategories(product.getCategories());
            product1.setInputPrice(product.getInputPrice());
            product1.setOutputPrice(product.getOutputPrice());
            product1.setInputDate(product.getInputDate());
            product1.setLongDescription(product.getLongDescription());
            product1.setShortDescription(product.getShortDescription());
            product1.setQuantity(product.getQuantity());
            product1.setUpdateDate(product.getUpdateDate());
            iProductRepo.save(product1);
        } else {
            throw new IllegalArgumentException("Product not found whit id: "+productId);
        }
    }

    @Override
    public Page<Product> getAll(String name, Pageable pageable){

        return iProductRepo.findAllByNameContaining(name,pageable);
    }

    @Override
    public List<Product> getAll(){
        return iProductRepo.findAll();
    }

    @Override
    public List<Product> getAllByCateId(Long id){
        Categories categories = iCategoriesService.getById(id);
        return iProductRepo.findAllByCategories(categories);
    }
}
