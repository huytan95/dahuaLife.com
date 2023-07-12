package com.example.demosecurityjwt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @Column(unique = true)
    private String name;
    private double inputPrice;
    private double outputPrice;
    private double discount;
    private int quantity;
    private Date inputDate;
    private Date updateDate;
    @Column(columnDefinition = "TEXT")
    private String shortDescription;
    @Column(columnDefinition = "TEXT")
    private String longDescription;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "cate_id")
    private Categories categories;
    @OneToMany(mappedBy = "product")
    private Set<Image> imageSet = new HashSet<>();
}
