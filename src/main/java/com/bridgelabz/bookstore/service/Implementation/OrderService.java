package com.bridgelabz.bookstore.service.Implementation;

import com.bridgelabz.bookstore.dto.OrderDto;
import com.bridgelabz.bookstore.exception.BookException;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.model.Order;
import com.bridgelabz.bookstore.model.OrderBook;
import com.bridgelabz.bookstore.model.Response;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.repository.OrderRepository;
import com.bridgelabz.bookstore.service.IOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService implements IOrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private Environment environment;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    BookRepository bookRepository;

    @Override
    public Response addOrder(OrderDto orderDto) {
        Order order = mapper.map(orderDto, Order.class);
        for (OrderBook orderBook : order.getBooks()) {
            orderBook.setOrder(order);
            orderBook.getBook().getOrders().add(orderBook);
        }
        order.setDate(order.getDate());
        orderRepository.save(order);
        Response response=new Response(200, environment.getProperty("book.success.message"));
        return response;
    }

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
        Response response1=new Response(200, environment.getProperty("book.success.delete.message"));
        return response1;
    }
}
