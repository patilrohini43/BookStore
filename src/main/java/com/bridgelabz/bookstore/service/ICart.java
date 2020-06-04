package com.bridgelabz.bookstore.service;


import com.bridgelabz.bookstore.model.Cart;
import com.bridgelabz.bookstore.model.CartDetails;
import com.bridgelabz.bookstore.model.Response;

import java.util.List;

public interface ICart {

    Response createCart(String token);

    Response addToCart(Long bookId,String token);

    Response removeFromCart(Long bookId);

    List<Cart> getAllCartList();
    public List<CartDetails> getAllCartDetailsList(String token);

    Response increMentQuantity(Long cartId);

    Response decreMentQuantity(Long cartId);
}
