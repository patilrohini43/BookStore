package com.bridgelabz.bookstore.service.Implementation;

import com.bridgelabz.bookstore.dto.AddressDto;
import com.bridgelabz.bookstore.dto.LoginDto;
import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.exception.BookException;
import com.bridgelabz.bookstore.model.Address;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.model.Response;
import com.bridgelabz.bookstore.model.User;
import com.bridgelabz.bookstore.repository.AddressRepository;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.service.IUserService;
import com.bridgelabz.bookstore.utility.Email;
import com.bridgelabz.bookstore.utility.UserToken;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    private Environment environment;


    @Override
    public Response registerUser(UserDto userDto) {
       userRepository.findUserByEmail(userDto.getEmail())
                .orElseThrow(() -> new BookException(401, "Already Exist"));
        User user = mapper.map(userDto, User.class);
        userRepository.save(user);
        String url=UserToken.createToken(user.getUserId());
        Email.sendEmail("patilrohini43@gmail.com","Verification Mail",url);
        Response response=new Response(200, environment.getProperty("user.success.message"));
        return response;
    }

    public Response verifyToken(String token)
    {
        System.out.println(token);

        Long userID = UserToken.tokenVerify(token);
        System.out.println(userID);
        User user=userRepository.findById(userID)
                .orElseThrow(() -> new BookException(401, "token.error"));

        user.setStatus(true);
        userRepository.save(user);

        Response response=new Response(200,"Verify Successfully");
        return response;

    }

    @Override
    public Response login(LoginDto loginDto) {
        User user=userRepository.findUserByEmail(loginDto.getEmail())
                .orElseThrow(() -> new BookException(401, "User Not Found"));
        if(user.isStatus()){
            if(user.getPassword().equals(loginDto.getPassword())){
                String token= UserToken.createToken(user.getUserId());
                Response response=new Response(200,"Login Successfully",token);
                return response;
            }
            throw new BookException(405,"passWord not match");
        }
        throw new BookException(405,"need to verfiy token");
    }

    @Override
    public Response customerDetails(String token, AddressDto addressDto) {
        Long userID = UserToken.tokenVerify(token);
        System.out.println(userID);
        User user=userRepository.findById(userID)
                .orElseThrow(() -> new BookException(401, "token.error"));
        Address address = mapper.map(addressDto, Address.class);
        address.setUser(user);
        user.getAddressList().add(address);
        System.out.println(address.getUser().getUserId());
        addressRepository.save(address);
        userRepository.save(user);
        Response response1=new Response(200, environment.getProperty("customer.success.message"));
        return response1;
    }



}
