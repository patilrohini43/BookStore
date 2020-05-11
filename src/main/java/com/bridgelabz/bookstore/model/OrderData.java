package com.bridgelabz.bookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "`OrderData`")
public class OrderData {

    @Id
    @Column(name = "orderId")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long orderId;

    @Column(name = "orderStatus")
    private boolean status;

    @Column(name="orderDate")
    private LocalDate orderDate;

    @Column(name="totalPrice")
    private double totalPrce;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "addressId")
    private Address address;

    @ManyToOne()
    @JoinColumn(name="cartId")
    private CartDetails cart;


    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalPrce() {
        return totalPrce;
    }

    public void setTotalPrce(double totalPrce) {
        this.totalPrce = totalPrce;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CartDetails getCart() {
        return cart;
    }

    public void setCart(CartDetails cart) {
        this.cart = cart;
    }


}
