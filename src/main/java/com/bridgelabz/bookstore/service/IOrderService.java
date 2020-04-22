package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.AddressDto;
import com.bridgelabz.bookstore.dto.OrderDto;
import com.bridgelabz.bookstore.model.Address;
import com.bridgelabz.bookstore.model.OrderBook;
import com.bridgelabz.bookstore.model.Response;

import java.util.List;

public interface IOrderService {
    Response placeOrder(Long bookId, int quantity);

    public Response orderItem(String token,Long orderId,Long addressId);

    Response removeOrder(Long bookId);

    List<Address> addressListByOrderId(String token,Long orderId);

    List<Address> getAddressDetailById(String token, Long addressId);

    //public Response orderItem(String token,Long orderId,Long addressId);
    public List<OrderBook> orderIdWiseBookDetails(String token, Long orderId);


}
