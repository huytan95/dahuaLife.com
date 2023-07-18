package com.example.demosecurityjwt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String url;
    @ManyToOne
    @JoinColumn(name = "productId")
    @JsonIgnore
    private Product product;
    @ManyToOne
    @JoinColumn(name = "webId")
    @JsonIgnore
    private WebInformation webInformation;
}
