package com.bridgelabz.bookstore.repository;

import com.bridgelabz.bookstore.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {
}
