package com.example.demosecurityjwt.controller;

import com.example.demosecurityjwt.dto.OrderRequest;
import com.example.demosecurityjwt.model.Orders;
import com.example.demosecurityjwt.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class OrderController {

    private final IOrderService iOrderService;

    @PostMapping("order")
    public ResponseEntity<Orders> addOrder(@RequestBody OrderRequest orderRequest){
        Orders orders = iOrderService.convertToOrder(orderRequest);
        return ResponseEntity.ok().body(iOrderService.saveOrder(orders));
    }

    @GetMapping("admin/order/{id}")
    public ResponseEntity<Orders> getOrder(@PathVariable Long id){
        return ResponseEntity.ok().body(iOrderService.getOrderById(id));
    }

    @PostMapping("admin/order/{id}")
    public ResponseEntity<?> updateStatus(@PathVariable Long id,
                                               @RequestParam("status") String status){
        return ResponseEntity.ok().body(iOrderService.updateStatus(id, status));
    }

    @GetMapping("admin/order")
    public ResponseEntity<List<Orders>> getOrderByMonth(@RequestParam(value = "month", required = false) Integer month,
                                                        @RequestParam(value = "year", required = false) Integer year){
        if(month == null && year == null){
            return ResponseEntity.ok().body(iOrderService.getAllOrder());
        }
        return ResponseEntity.ok().body(iOrderService.getOrderByMonth(month,year));
    }
}
