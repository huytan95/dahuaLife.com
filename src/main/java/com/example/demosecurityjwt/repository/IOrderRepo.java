package com.example.demosecurityjwt.repository;

import com.example.demosecurityjwt.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IOrderRepo extends JpaRepository<Orders, Long> {
    @Query("SELECT o FROM Orders o WHERE MONTH(o.orderDate) = :month AND YEAR(o.orderDate) = :year")
    List<Orders> findAllOrdersInMonth(@Param("month") int month, @Param("year") int year);
}
