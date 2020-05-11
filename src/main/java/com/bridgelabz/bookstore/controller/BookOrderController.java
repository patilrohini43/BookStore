package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.model.OrderData;
import com.bridgelabz.bookstore.model.Response;
import com.bridgelabz.bookstore.service.IBookOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@PropertySource("classpath:message.properties")
@RequestMapping(value = "/order")
@CrossOrigin(origins="http://localhost:3000")
public class BookOrderController {

    @Autowired
    IBookOrderService iBookOrder;

    @PostMapping
    public ResponseEntity<Response> orderItem(@RequestHeader("token") String token, @RequestParam Long cartId, @RequestParam Long addressId){
        Response response= iBookOrder.orderItem(token,cartId,addressId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<OrderData>> orderList(@RequestHeader("token") String token,@RequestParam Long orderId)
    {
        List<OrderData> orderDataList = iBookOrder.orderList(token, orderId);
        return new ResponseEntity<>(orderDataList, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Response> placeOrder(@RequestHeader("token") String token, @RequestParam Long orderId){
        Response response= iBookOrder.placeOrder(token,orderId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
