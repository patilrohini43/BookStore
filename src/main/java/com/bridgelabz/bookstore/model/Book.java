package com.bridgelabz.bookstore.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyToOne;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Book")
public class Book {

        @Id
        @GeneratedValue(strategy=GenerationType.AUTO)
        @Column(name = "bookId")
        private Long bookId;

        @Column(name = "bookName")
        private String bookName;

        @Column(name = "author")
        private String author;

        @Column(name = "price")
        private double price;

        @Column(name = "bookDetail")
        private String bookDetail;

        @Column(name = "image")
        private String image;

    @JsonIgnore
    @OneToMany(
            mappedBy = "book",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<OrderBook> orders = new ArrayList<>();

    public List<OrderBook> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderBook> orders) {
        this.orders = orders;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBookDetail() {
        return bookDetail;
    }

    public void setBookDetail(String bookDetail) {
        this.bookDetail = bookDetail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
