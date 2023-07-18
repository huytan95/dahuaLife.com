package com.example.demosecurityjwt.service.impl;

import com.example.demosecurityjwt.model.WebInformation;
import com.example.demosecurityjwt.repository.WebInformationRepo;
import com.example.demosecurityjwt.service.WebInforService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WebInforServiceImpl implements WebInforService {

    private final WebInformationRepo webInformationRepo;

    @Override
    public WebInformation getById(Long id){
        return webInformationRepo.findById(id).orElse(null);
    }

    @Override
    public WebInformation addWebInfor(WebInformation webInformation){
        return webInformationRepo.save(webInformation);
    }

    @Override
    public WebInformation updateWebInfor(Long id, WebInformation webInformation){
        WebInformation webInformation1 = getById(id);
        if(webInformation1 == null){
            throw new IllegalArgumentException("WebInfor not found");
        }
        webInformation1.setName(webInformation.getName());
        webInformation1.setEmail(webInformation.getEmail());
        webInformation1.setAddress(webInformation.getAddress());
        webInformation1.setPhoneNumber(webInformation.getPhoneNumber());
        webInformation1.setUrlLogo(webInformation.getUrlLogo());
        webInformation1.setImageBanner(webInformation.getImageBanner());
        return webInformationRepo.save(webInformation1);
    }
}
