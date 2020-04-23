package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.AddressDto;
import com.bridgelabz.bookstore.dto.LoginDto;
import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.model.Address;
import com.bridgelabz.bookstore.model.Response;

public interface IUserService {
    Response registerUser(UserDto userDto);
    public Response verifyToken(String token);

    Response login(LoginDto loginDto);

    Response customerDetails(String token, AddressDto addressDto);

    Response updateCustomerAddress(String token, Long addressId, AddressDto addressDto);

    Address getData(String token, Long addressId);
}
