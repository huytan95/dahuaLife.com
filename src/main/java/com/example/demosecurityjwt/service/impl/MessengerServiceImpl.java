package com.example.demosecurityjwt.service.impl;

import com.example.demosecurityjwt.model.Messenger;
import com.example.demosecurityjwt.repository.IMessengerRepo;
import com.example.demosecurityjwt.service.IMessengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MessengerServiceImpl implements IMessengerService {

    public final IMessengerRepo iMessengerRepository;

    @Override
    public Page<Messenger> listMessenger(Pageable pageable) {

        return iMessengerRepository.findAll(pageable);
    }

    @Override
    public Messenger getMessById(Long id) {
        Optional<Messenger> messengerOptional = iMessengerRepository.findById(id);
        return messengerOptional.orElse(null);
    }

    @Override
    public Messenger saveMessenger(Messenger messenger) {
        return iMessengerRepository.save(messenger);
    }
}
