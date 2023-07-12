package com.example.demosecurityjwt.controller;

import com.example.demosecurityjwt.model.Image;
import com.example.demosecurityjwt.repository.IImageRepository;
import com.example.demosecurityjwt.service.IImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("admin/image")
public class ImageController {

    public final IImageService iImageService;

    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestParam("image") MultipartFile file) throws IOException {
        Image image = new Image();
       image = iImageService.convertFileToImage(file);
        iImageService.saveImage(image);
        return ResponseEntity.ok("success");
    }

}
