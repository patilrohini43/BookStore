package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.OrderDto;
import com.bridgelabz.bookstore.model.Response;

public interface IOrderService {
    Response addOrder(OrderDto order);
    Response addBookToCart(Long bookId, int quantity);
}
