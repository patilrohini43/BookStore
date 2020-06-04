package com.bridgelabz.bookstore.controller;

import com.bridgelabz.bookstore.model.Cart;
import com.bridgelabz.bookstore.model.CartDetails;
import com.bridgelabz.bookstore.model.Response;
import com.bridgelabz.bookstore.service.ICart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@PropertySource("classpath:message.properties")
@RequestMapping(value = "/cart")
@CrossOrigin(origins="http://localhost:3000")
public class CartController {

    @Autowired
    ICart cart;

   @PostMapping("/createCart/{token}")
       public ResponseEntity<Response> createCart(@PathVariable(name="token") String token) {
       Response response= cart.createCart(token);
       return new ResponseEntity<>(response, HttpStatus.OK);
   }



    @PostMapping("/addtoCart/{bookId}")
    public ResponseEntity<Response> addToCart(@PathVariable(name = "bookId") Long bookId,@RequestHeader("token") String token) {
        Response response= cart.addToCart(bookId,token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/deleteFromCart/{bookId}")
    public ResponseEntity<Response> removeFromCart(@PathVariable(name = "bookId") Long bookId) {
        Response response= cart.removeFromCart(bookId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("/increMentQuantity/{cartId}")
    public ResponseEntity<Response>  increMentQuantity(@PathVariable(name="cartId") Long cartId){
        Response response= cart.increMentQuantity(cartId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("/decreMentQuantity/{cartId}")
    public ResponseEntity<Response>  decreMentQuantity(@PathVariable(name="cartId") Long cartId){
        Response response= cart.decreMentQuantity(cartId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/getAllCart1")
    public List<Cart> getAllCartList() {
        return cart.getAllCartList();
    }

    @GetMapping("/getAllCart")
    public List<CartDetails> getAllCartDetailsList(@RequestHeader("token") String token) {
        return cart.getAllCartDetailsList(token);
    }
}
