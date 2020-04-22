package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.dto.AddressDto;
import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.dto.OrderDto;
import com.bridgelabz.bookstore.model.Address;
import com.bridgelabz.bookstore.model.Order;
import com.bridgelabz.bookstore.model.OrderBook;
import com.bridgelabz.bookstore.model.Response;
import com.bridgelabz.bookstore.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/addressList/{token}")
    public List<Address> addressList(@PathVariable String token,@RequestParam Long orderId) {
        return iOrderService.addressListByOrderId(token,orderId);
    }

    @GetMapping("/{token}")
    public List<Address> getAddressDetailById(@PathVariable String token,@RequestParam Long addressId) {
        return iOrderService.getAddressDetailById(token,addressId);
    }

    @PostMapping("/orderPlace/{token}")
    public ResponseEntity<Response> orderItem(@PathVariable String token,@RequestParam Long orderId,@RequestParam Long addressId){
        Response response= iOrderService.orderItem(token,orderId,addressId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/list/{token}")
    public List<OrderBook> bookorder(@PathVariable String token, @RequestParam Long orderId) {
        return iOrderService.orderIdWiseBookDetails(token,orderId);
    }
}
