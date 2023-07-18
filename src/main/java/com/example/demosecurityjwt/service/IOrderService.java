package com.example.demosecurityjwt.service;

import com.example.demosecurityjwt.dto.OrderRequest;
import com.example.demosecurityjwt.model.Orders;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IOrderService {
    Orders saveOrder(Orders orders);

    Orders getOrderById(Long id);

    Orders convertToOrder(OrderRequest orderRequest);

    List<Orders> getAllOrder();

    Orders updateStatus(Long id, String orderStatus);

    List<Orders> getOrderByMonth(int month, int year);
}
