package com.bridgelabz.bookstore.service.Implementation;

import com.bridgelabz.bookstore.exception.BookException;
import com.bridgelabz.bookstore.model.*;
import com.bridgelabz.bookstore.repository.*;
import com.bridgelabz.bookstore.service.IBookOrderService;
import com.bridgelabz.bookstore.utility.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookOrderService implements IBookOrderService {

    @Autowired
    CartDetailRepository cartDetailRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookOrderRepository bookOrderRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    Environment environment;

    @Override
    public Response orderItem(String token, Long cartId, Long addressId) {
        Long userID = UserToken.tokenVerify(token);
        User user=userRepository.findById(userID)
                .orElseThrow(() -> new BookException(401, "token.error"));
        CartDetails cartDetails=cartDetailRepository.findById(cartId) .orElseThrow(() -> new BookException(401, "CartID not found"));
        Address address=addressRepository.findById(addressId) .orElseThrow(() -> new BookException(401, "AddressId not found"));
        List<Cart> list = cartRepository.findAll().stream().filter(cart -> cart.getCart().getId().equals(cartId)).collect(Collectors.toList());
        double total=0;
        double sum =0;
        for (Cart cart:list) {
            System.out.println(cart.getBook().getBookId()+"bookId");
             total =  cart.getBook().getPrice() * cart.getQuantity();
             sum=sum+total;
        }
        System.out.println("total book price" +sum);
        OrderData orderData=new OrderData();
        orderData.setUser(user);
        orderData.setAddress(address);
        orderData.setTotalPrce(sum);
        orderData.setCart(cartDetails);
        orderData.setOrderDate(LocalDate.now());
        bookOrderRepository.save(orderData);
        System.out.println(orderData.getOrderId()+"orderID");
        Response response1=new Response(200, environment.getProperty("order.added.message"),String.valueOf(orderData.getOrderId()));
        return response1;
    }

    @Override
    public List<OrderData> orderList(String token, Long orderId) {
        List<OrderData> data;
        Long userID = UserToken.tokenVerify(token);
        User user=userRepository.findById(userID)
                .orElseThrow(() -> new BookException(401, "token.error"));
        data=bookOrderRepository.findAll().stream().filter(orderData -> orderData.getOrderId().equals(orderId)).collect(Collectors.toList());
        if(data == null){
            throw new BookException(401,"List Empty");
        }
        return data;
    }

    @Override
    public Response placeOrder(String token, Long orderId) {
        Long userID = UserToken.tokenVerify(token);
        User user=userRepository.findById(userID)
                .orElseThrow(() -> new BookException(401, "token.error"));
        OrderData orderData=bookOrderRepository.findById(orderId).orElseThrow(() -> new BookException(401, "Order Id Not Found"));
        orderData.setStatus(true);
        bookOrderRepository.save(orderData);
        Response response1=new Response(200, environment.getProperty("order.success.message"), Long.toString(orderData.getOrderId()));
        return response1;
    }
}
