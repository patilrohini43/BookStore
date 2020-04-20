package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.OrderDto;
import com.bridgelabz.bookstore.model.Response;

public interface IOrderService {
    Response addOrder(OrderDto order);
    Response placeOrder(Long bookId, int quantity);

    Response removeOrder(Long bookId);
}
