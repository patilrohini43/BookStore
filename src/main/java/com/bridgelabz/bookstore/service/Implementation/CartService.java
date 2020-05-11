package com.bridgelabz.bookstore.service.Implementation;

import com.bridgelabz.bookstore.exception.BookException;
import com.bridgelabz.bookstore.model.*;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.repository.CartDetailRepository;
import com.bridgelabz.bookstore.repository.CartRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.service.ICart;
import com.bridgelabz.bookstore.utility.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService implements ICart {


    @Autowired
    BookRepository bookRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    CartDetailRepository cartDetailRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private Environment environment;


    @Override
    public Response createCart(String token) {
        Long userID = UserToken.tokenVerify(token);
        User user=userRepository.findById(userID)
                .orElseThrow(() -> new BookException(401, "token.error"));
        CartDetails car=new CartDetails();
        car.setUser(user);
        cartDetailRepository.save(car);
        Response response1=new Response(200, "added cart");
        return response1;
    }

    @Override
    public Response addToCart(Long bookId,Long cartId) {
        Book book= bookRepository.findById(bookId).orElseThrow(()->new BookException(400,"Book Id Not Found"));
        CartDetails cartDetails=cartDetailRepository.findById(cartId).orElseThrow(()->new BookException(400,"Cart Id Not Found"));
        List<Cart> cartBook = cartRepository.findAll().stream().filter(cart -> cart.getBook().getBookId().equals(bookId)).collect(Collectors.toList());
        if(!cartBook.isEmpty()){
            throw new BookException(400,"Book Already Added");
        }
        Cart cart=new Cart(cartDetails);
        cart.setBook(book);
        cart.setCartStatus(true);
        cart.setQuantity(1);
        cart.setCart(cartDetails);
        cartDetails.getItems().add(cart);
        cartRepository.save(cart);
        cartDetailRepository.save(cartDetails);
        Response response1=new Response(200, environment.getProperty("book.success.cart.message"));
        return response1;
    }

    @Override
    public List<Cart> getAllCartList() {
        List<Cart> cartBook = cartRepository.findAll().stream().filter(cart -> cart.isCartStatus() == true).collect(Collectors.toList());
        return cartBook;
    }

    @Override
    public List<CartDetails> getAllCartDetailsList(String token) {
        Long userID = UserToken.tokenVerify(token);
        User user=userRepository.findById(userID)
                .orElseThrow(() -> new BookException(401, "token.error"));
        List<CartDetails> cartBook = cartDetailRepository.findAll().stream().filter(cartDetails -> cartDetails.getUser().getUserId().equals(userID)).collect(Collectors.toList());
        return cartBook;
    }

    @Override
    public Response increMentQuantity(Long cartId) {
        Cart cart= cartRepository.findById(cartId).orElseThrow(()->new BookException(400,"Book Id Not Found"));
        int updateQuantity = cart.getQuantity();
        if(cart.getQuantity() < 9 ){
            int newQuanity=updateQuantity + 1;
            cart.setQuantity(newQuanity);
            cartRepository.save(cart);
            Response response1=new Response(200, environment.getProperty("update.quanity.sucess"));
            return response1;
        }
        throw new BookException(400,"Not Accpected more than 9 quantity");
    }

    @Override
    public Response decreMentQuantity(Long cartId) {
        Cart cart= cartRepository.findById(cartId).orElseThrow(()->new BookException(400,"Book Id Not Found"));
        int updateQuantity = cart.getQuantity();
        if(cart.getQuantity() > 0 ){
            int newQuanity=updateQuantity - 1;
            cart.setQuantity(newQuanity);
            cartRepository.save(cart);
            Response response1=new Response(200, environment.getProperty("update.quanity.sucess"));
            return response1;
        }
       throw new BookException(400,"Not Accpected less than 0 quantity");
    }

    @Override
    public Response removeFromCart(Long cartId) {
        Cart cart= cartRepository.findById(cartId).orElseThrow(()->new BookException(400,"Cart Id Not Found"));
        cartRepository.delete(cart);
        Response response1=new Response(200, environment.getProperty("book.remove.cart.message"));
        return response1;
    }
}
