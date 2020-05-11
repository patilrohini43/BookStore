package com.bridgelabz.bookstore.repository;

import com.bridgelabz.bookstore.model.CartDetails;
import com.bridgelabz.bookstore.service.Implementation.CartService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDetailRepository extends JpaRepository<CartDetails,Long> {
}
