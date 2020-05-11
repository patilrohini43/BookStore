package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.model.OrderData;
import com.bridgelabz.bookstore.model.Response;

import java.util.List;

public interface IBookOrderService {
    Response orderItem(String token, Long cartId, Long addressId);
    List<OrderData> orderList(String token, Long orderId);
    Response placeOrder(String token, Long orderId);
}
