package com.bridgelabz.bookstore.controller;


import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.model.Response;
import com.bridgelabz.bookstore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<Response> registration(@RequestBody UserDto userDto){
        Response response=userService.registerUser(userDto);
    return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
