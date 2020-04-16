package com.bridgelabz.bookstore.model;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @Column(name = "userId")
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long userId;

    @Column(name = "userName")
    String userName;

    @Column(name = "email")
    String email;

    @Column(name = "password")
    String password;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
