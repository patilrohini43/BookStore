package com.bridgelabz.bookstore.service.Implementation;

import com.bridgelabz.bookstore.dto.AddressDto;
import com.bridgelabz.bookstore.exception.BookException;
import com.bridgelabz.bookstore.model.*;
import com.bridgelabz.bookstore.repository.AddressRepository;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.repository.OrderRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.service.IOrderService;
import com.bridgelabz.bookstore.utility.UserToken;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

//    @Autowired
//    AddressRepository addressRepository;

    @Autowired
    private Environment environment;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    BookRepository bookRepository;

    @Override
    public Response placeOrder(Long bookId, int quantity) {
        Book book=bookRepository.findById(bookId).orElseThrow(()->new BookException(400,"book Id not found"));
        OrderBook orderBook = new OrderBook(book);
        Order order=new Order();
        order.setQuantity(quantity);
        orderBook.setBook(book);
        orderBook.setOrder(order);
        book.getOrders().add(orderBook);
        orderRepository.save(order);
        bookRepository.save(book);
        Response response1=new Response(200, environment.getProperty("book.success.message"));
        return response1;
    }


    @Override
    public Response orderItem(String token,Long orderId,Long addressId) {
        Long userID = UserToken.tokenVerify(token);
        System.out.println(userID);
        User user=userRepository.findById(userID)
                .orElseThrow(() -> new BookException(401, "token.error"));
        Order order=orderRepository.findById(orderId).orElseThrow(()->new BookException(400,"Order Id not found"));
        List<Order> orderList=orderRepository.findAll().stream().filter(order1 -> order1.getOrderId().equals(orderId)).collect(Collectors.toList());
        double price = 0;
        for(int i=0;i<orderList.size();i++){
             price = order.getBooks().get(i).getBook().getPrice();
            System.out.println(price);
        }
        double totalPrice = order.getQuantity() * price;
        order.setUser(user);
        order.setPrice(totalPrice);
        order.setStatus(true);
        order.setAddressId(addressId);
        orderRepository.save(order);
        Response response1=new Response(200, environment.getProperty("order.success.message"), Long.toString(order.getOrderId()));
        return response1;
    }



    @Override
    public Response removeOrder(Long orderId) {
        Order orderItem=orderRepository.findById(orderId).orElseThrow(()->new BookException(400,"book Id not found"));
        OrderBook orderBook = new OrderBook(orderItem);
        orderItem.getBooks().remove(orderBook);
        orderRepository.delete(orderItem);
        Response response1=new Response(200, environment.getProperty("book.success.delete.message"));
        return response1;
    }


    @Override
    public List<Address> addressListByOrderId(String token,Long orderId) {
        Long userID = UserToken.tokenVerify(token);
        System.out.println(userID);
        User user=userRepository.findById(userID)
                .orElseThrow(() -> new BookException(401, "token.error"));
        Order order=orderRepository.findById(orderId).orElseThrow(()->new BookException(400,"Order Id not found"));
        return  order.getUser().getAddressList();
    }

    @Override
    public List<Order> orderIdWiseBookDetails(String token,Long orderId) {
        Long userID = UserToken.tokenVerify(token);
        System.out.println(userID);
        User user=userRepository.findById(userID)
                .orElseThrow(() -> new BookException(401, "token.error"));
        Order order=orderRepository.findById(orderId).orElseThrow(()->new BookException(400,"Order Id not found"));
        List<Order> orderList=orderRepository.findAll().stream().filter(data->data.getOrderId()==orderId).collect(Collectors.toList());
        return orderList;
    }
}
