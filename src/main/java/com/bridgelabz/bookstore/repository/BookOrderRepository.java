package com.bridgelabz.bookstore.repository;

import com.bridgelabz.bookstore.model.OrderData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookOrderRepository extends JpaRepository<OrderData,Long> {
}
