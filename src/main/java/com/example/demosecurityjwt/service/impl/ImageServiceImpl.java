package com.example.demosecurityjwt.service.impl;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.demosecurityjwt.model.Image;
import com.example.demosecurityjwt.model.Product;
import com.example.demosecurityjwt.repository.IImageRepository;
import com.example.demosecurityjwt.service.IImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ImageServiceImpl implements IImageService {

    public final IImageRepository iImageRepository;
    public final AmazonS3 s3Client;

    @Value("${application.bucket.name}")
    private String bucketName;

    @Override
    public String uploadAndGetUrl(MultipartFile file) {
        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());

            String key = generateFileName(file);
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, file.getInputStream(), metadata);
            s3Client.putObject(putObjectRequest);
            s3Client.setObjectAcl(bucketName, key, CannedAccessControlList.PublicRead);
            return s3Client.getUrl(bucketName, key).toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String generateFileName(MultipartFile multiPart) {
        return (new Date().getTime()) + "_" + Objects.requireNonNull(multiPart.getOriginalFilename()).replace(" ", "_");
    }

    @Override
    public void saveImage(Image image){
        iImageRepository.save(image);
    }

    @Override
    public Image convertFileToImage(MultipartFile file){
        Image image = new Image();
        image.setName(file.getOriginalFilename());
        String url = uploadAndGetUrl(file);
        image.setUrl(url);
        return image;
    }
    @Override
    public Image getImageByName(String name) {
        return iImageRepository.findByName(name);
    }

    @Override
    public void addImageToProduct(Product product, Image image){
        product.getImageSet().add(image);
    }

    @Override
    public Image getImageById(Long id){
        Optional<Image> optionalImage = iImageRepository.findById(id);
        return optionalImage.orElse(null);
    }

    @Override
    public Image updateImage(Long id, Image image){
        Optional<Image> optionalImage = iImageRepository.findById(id);
        Image image1 = new Image();
        if(optionalImage.isPresent()){
            image1.setName(image.getName());
            image1.setUrl(image.getUrl());
            return iImageRepository.save(image1);
        } else {
            throw new IllegalArgumentException("Image not found");
        }
    }
}
