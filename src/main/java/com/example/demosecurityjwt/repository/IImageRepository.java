package com.example.demosecurityjwt.repository;

import com.example.demosecurityjwt.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImageRepository extends JpaRepository<Image, Long> {
    Image findByName(String name);
}
