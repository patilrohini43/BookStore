package com.bridgelabz.bookstore.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "`order`")
public class Order implements Serializable {

    @Id
    @Column(name = "orderId")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long orderId;

    @Column(name = "status")
    private boolean status;

    @Column(name = "date")
    private Date date;

    @Column(name = "price")
    private double price;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "addressId")
    private Long addressId;

    @OneToMany(
            mappedBy = "order",
            cascade = CascadeType.ALL
    )
    private List<OrderBook> books = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;


    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public List<OrderBook> getBooks() {
        return books;
    }

    public void setBooks(List<OrderBook> books) {
        this.books = books;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public long getId() {
        return orderId;
    }

    public void setId(long orderId) {
        this.orderId = orderId;
    }



    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
