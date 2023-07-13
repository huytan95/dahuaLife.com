package com.example.demosecurityjwt.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long orderId;
    private String name;
    private String phoneNumber;
    private String cityName;
    private String districts;
    private String commune;
    private String address;
    private Date orderDate;
    private Date updateStatus;
    private double totalPrice;
    private OrderStatus status;
    private String messenger;
    private double shipPrice;

    @ManyToMany
    @JoinTable(name = "order_product", joinColumns = @JoinColumn(name = "order_id"),
    inverseJoinColumns = @JoinColumn(name = "product_Id"))
    private Set<Product> products = new HashSet<>();
}
