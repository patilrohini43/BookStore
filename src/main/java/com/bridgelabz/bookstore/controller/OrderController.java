package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.dto.OrderDto;
import com.bridgelabz.bookstore.model.Order;
import com.bridgelabz.bookstore.model.Response;
import com.bridgelabz.bookstore.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

    @Autowired
    IOrderService iOrderService;

    @PostMapping("/placeOrder/{bookId}/{quantity}")
    public ResponseEntity<Response> placeOrder(@PathVariable(name = "bookId") Long bookId,@PathVariable int quantity) {
        Response response= iOrderService.placeOrder(bookId,quantity);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @DeleteMapping("/removeOrder/{bookId}/{quantity}")
    public ResponseEntity<Response> removeOrder(@PathVariable(name = "bookId") Long bookId) {
        Response response= iOrderService.removeOrder(bookId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
