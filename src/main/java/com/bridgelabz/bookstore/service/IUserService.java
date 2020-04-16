package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.model.Response;

public interface IUserService {
    Response registerUser(UserDto userDto);
}
