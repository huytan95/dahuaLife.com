package com.example.demosecurityjwt.controller;

import com.example.demosecurityjwt.model.Image;
import com.example.demosecurityjwt.model.WebInformation;
import com.example.demosecurityjwt.service.IImageService;
import com.example.demosecurityjwt.service.WebInforService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
    @RequestMapping("admin/web-Information")
public class WebInforController {

    private final WebInforService webInforService;
    private final IImageService iImageService;

    @PostMapping
    @Transactional
    public ResponseEntity<WebInformation> addWebInfor(@RequestBody WebInformation webInformation,
                                                      @RequestParam("banners") Long[] banners,
                                                      @RequestParam("logo") long id){
        for (Long banner : banners) {
            Image image = iImageService.getImageById(banner);
            webInformation.getImageBanner().add(image);
            image.setWebInformation(webInformation);

        }
        Image image = iImageService.getImageById(id);
        webInformation.setUrlLogo(image.getUrl());
        return ResponseEntity.ok().body(webInforService.addWebInfor(webInformation));
    }

    @GetMapping("{webId}")
    public ResponseEntity<WebInformation> getById(@PathVariable Long webId){
        return ResponseEntity.ok().body(webInforService.getById(webId));
    }

    @GetMapping
    public ResponseEntity<WebInformation> updateInfor(@RequestParam("id") Long id,
                                                      @RequestBody WebInformation webInformation){
        return ResponseEntity.ok().body(webInforService.updateWebInfor(id,webInformation));
    }
}
