package com.example.demosecurityjwt.repository;

import com.example.demosecurityjwt.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderRepo extends JpaRepository<Orders, Long> {
}
