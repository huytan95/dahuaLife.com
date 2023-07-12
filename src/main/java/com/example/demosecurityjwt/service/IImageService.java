package com.example.demosecurityjwt.service;

import com.example.demosecurityjwt.model.Image;
import com.example.demosecurityjwt.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public interface IImageService {
    String uploadAndGetUrl(MultipartFile file) throws IOException;

    void saveImage(Image image);

    Image convertFileToImage(MultipartFile file);

    Image getImageByName(String name);

    void addImageToProduct(Product product, Image image);

    Image getImageById(Long id);

    Image updateImage(Long id, Image image);
}
