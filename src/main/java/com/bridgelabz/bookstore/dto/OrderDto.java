package com.bridgelabz.bookstore.dto;

import javax.persistence.Lob;

public class OrderDto {

    private Long quanity;

    public Long getQuanity() {
        return quanity;
    }

    public void setQuanity(Long quanity) {
        this.quanity = quanity;
    }
}
