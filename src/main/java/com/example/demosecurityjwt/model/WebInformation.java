package com.example.demosecurityjwt.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class WebInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long webId;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    @OneToMany(mappedBy = "webInformation")
    private Set<Image> imageBanner = new HashSet<>();
    private String urlLogo;
}
