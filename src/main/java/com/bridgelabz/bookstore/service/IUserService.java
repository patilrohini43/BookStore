package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.AddressDto;
import com.bridgelabz.bookstore.dto.LoginDto;
import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.model.Response;

public interface IUserService {
    Response registerUser(UserDto userDto);
    public Response verifyToken(String token);

    Response login(LoginDto loginDto);

    Response customerDetails(String token, AddressDto addressDto);
}
