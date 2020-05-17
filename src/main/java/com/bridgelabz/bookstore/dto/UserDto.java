package com.bridgelabz.bookstore.dto;


import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Validated
public class UserDto {
    @NotNull(message="Can not be null")
    @Size(min=2, max=30)
    private String userName;

    @NotNull(message="Can not be null")
   private String email;

    @NotNull(message="Can not be null")
    private String password;

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
