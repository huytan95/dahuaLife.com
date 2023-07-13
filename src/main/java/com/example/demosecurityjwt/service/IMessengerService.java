package com.example.demosecurityjwt.service;

import com.example.demosecurityjwt.model.Messenger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface IMessengerService {
    Page<Messenger> listMessenger(Pageable pageable);

    Messenger getMessById(Long id);

    Messenger saveMessenger(Messenger messenger);
}
