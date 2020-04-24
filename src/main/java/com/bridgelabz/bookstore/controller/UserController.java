package com.bridgelabz.bookstore.controller;


import com.bridgelabz.bookstore.dto.AddressDto;
import com.bridgelabz.bookstore.dto.LoginDto;
import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.model.Address;
import com.bridgelabz.bookstore.model.Response;
import com.bridgelabz.bookstore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<Response> registration(@RequestBody UserDto userDto) throws IOException {
        Response response=userService.registerUser(userDto);
    return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/verify/{token}")
    public ResponseEntity<Response> verifyEmail(@PathVariable String token)
    {
        Response response=userService.verifyToken(token);
        System.out.println("Verify Successfully");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping(value = "/login")
    public ResponseEntity<Response> login(@RequestBody LoginDto loginDto)
    {
        Response response=userService.login(loginDto);
        System.out.println("Verify Successfully");
        return new ResponseEntity<>(response,HttpStatus.OK);

    }

    @PostMapping("/adderssDetail/{token}")
    public ResponseEntity<Response> customerDetails(@PathVariable String token, @RequestBody AddressDto addressDto) {
        System.out.println("fgfgfg");
        Response response= userService.customerDetails(token,addressDto);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/{token}")
    public Address getAddressIdWiseDetails(@PathVariable String token, @RequestParam Long addressId){
        return  userService.getData(token,addressId);

    }

    @PutMapping("/{token}/{addressId}")
    public ResponseEntity<Response> updateCustomerAddress(@PathVariable String token,@PathVariable Long addressId, @RequestBody AddressDto addressDto) {
        Response response= userService.updateCustomerAddress(token,addressId,addressDto);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
