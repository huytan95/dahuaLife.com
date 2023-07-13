package com.example.demosecurityjwt.controller;

import com.example.demosecurityjwt.model.Messenger;
import com.example.demosecurityjwt.service.IMessengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MessengerController {

    private final IMessengerService iMessengerService;

    @PostMapping("messenger")
    public ResponseEntity<Messenger> saveMessenger(@RequestBody Messenger messenger){
        return ResponseEntity.ok().body(iMessengerService.saveMessenger(messenger));
    }

    @GetMapping("admin/messenger")
    public ResponseEntity<Page<Messenger>> pageMessenger(@RequestParam("pageNumber") Integer pageNumber,
                                                         @RequestParam("pageSize") Integer pageSize){
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return ResponseEntity.ok().body(iMessengerService.listMessenger(pageable));
    }

    @GetMapping("admin/messenger/{id}")
    public ResponseEntity<Messenger> getMessenger(@PathVariable Long id){
        return ResponseEntity.ok().body(iMessengerService.getMessById(id));
    }
}
