package com.example.demosecurityjwt.service.impl;

import com.example.demosecurityjwt.dto.ItemDto;
import com.example.demosecurityjwt.dto.OrderRequest;
import com.example.demosecurityjwt.model.OrderStatus;
import com.example.demosecurityjwt.model.Orders;
import com.example.demosecurityjwt.model.Product;
import com.example.demosecurityjwt.repository.IOrderRepo;
import com.example.demosecurityjwt.service.IOrderService;
import com.example.demosecurityjwt.service.IProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Component
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements IOrderService {

    private final IOrderRepo iOrderRepo;
    private final IProductService iProductService;

    @Override
    public Orders saveOrder(Orders orders){
        return iOrderRepo.save(orders);
    }

    @Override
    public Orders getOrderById(Long id){
        Optional<Orders> optionalOrders = iOrderRepo.findById(id);
        return optionalOrders.orElse(null);
    }

    @Override
    public Orders convertToOrder(OrderRequest orderRequest){
        Orders orders = new Orders();
        double sum = 0;
        Set<ItemDto> itemDtoSet = orderRequest.getItemDtoSet();
        for(ItemDto itemDto : itemDtoSet){
            Product product = iProductService.getProductById(itemDto.getProductId());
            orders.getProducts().add(product);
            sum += product.getOutputPrice()*(1-product.getDiscount()/100)*itemDto.getQuantity();
        }
        orders.setName(orderRequest.getName());
        orders.setAddress(orderRequest.getAddress());
        orders.setCommune(orderRequest.getCommune());
        orders.setDistricts(orderRequest.getDistricts());
        orders.setCityName(orderRequest.getCityName());
        orders.setMessenger(orderRequest.getMessenger());
        orders.setOrderDate(new Date());
        orders.setPhoneNumber(orderRequest.getPhoneNumber());
        orders.setShipPrice(40000);
        orders.setTotalPrice(sum);
        orders.setStatus(OrderStatus.PLACED);
        return orders;
    }

    @Override
    public List<Orders> getAllOrder(){
        return iOrderRepo.findAll();
    }

    @Override
    public Orders updateStatus(Long id, String orderStatus){
        Orders orders = getOrderById(id);
        OrderStatus[] orderStatuses = OrderStatus.values();
        for(OrderStatus orderStatus1 : orderStatuses){
            if(orderStatus1.equals(getOrderStatus(orderStatus))){
                orders.setStatus(orderStatus1);
            }
        }
        return orders;
    }

    public OrderStatus getOrderStatus(String input) {
        try {
            return OrderStatus.valueOf(input);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("No constant "+ input);
        }
    }
}
