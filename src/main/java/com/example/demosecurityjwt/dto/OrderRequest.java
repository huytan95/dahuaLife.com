package com.example.demosecurityjwt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private String name;
    private String phoneNumber;
    private String cityName;
    private String districts;
    private String commune;
    private String address;
    private String messenger;
    private double shipPrice;
    private Set<ItemDto> itemDtoSet = new HashSet<>();
}
