package com.example.demosecurityjwt.service;

import com.example.demosecurityjwt.model.WebInformation;
import org.springframework.stereotype.Service;

@Service
public interface WebInforService {
    WebInformation getById(Long id);

    WebInformation addWebInfor(WebInformation webInformation);

    WebInformation updateWebInfor(Long id, WebInformation webInformation);
}
