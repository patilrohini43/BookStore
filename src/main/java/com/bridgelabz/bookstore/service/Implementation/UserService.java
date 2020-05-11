package com.bridgelabz.bookstore.service.Implementation;

import com.bridgelabz.bookstore.dto.AddressDto;
import com.bridgelabz.bookstore.dto.LoginDto;
import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.exception.BookException;
import com.bridgelabz.bookstore.exception.UserException;
import com.bridgelabz.bookstore.model.Address;
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
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.util.List;
import java.util.UUID;

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
       User userExist=userRepository.findUserByEmail(userDto.getEmail());
       if (userExist!=null){
           throw new UserException(401, "Already Exist");
       }
        User user = mapper.map(userDto, User.class);
        userRepository.save(user);
        String url=UserToken.createToken(user.getUserId());
        Email.sendEmail(userDto.getEmail(),"Verification Mail",url);
        Response response=new Response(200, environment.getProperty("user.success.message"));
        return response;
    }

    public Response verifyToken(String token)
    {
        System.out.println(token);

        Long userID = UserToken.tokenVerify(token);
        System.out.println(userID);
        User user=userRepository.findById(userID)
                .orElseThrow(() -> new UserException(401, "token.error"));

        user.setStatus(true);
        userRepository.save(user);

        Response response=new Response(200,"Verify Successfully");
        return response;

    }

    @Override
    public Response login(LoginDto loginDto) {
        User user=userRepository.findUserByEmail(loginDto.getEmail());
       if(user==null){
           throw new UserException(401, "User Not Found");
       }
        if(user.isStatus()){
            if(user.getPassword().equals(loginDto.getPassword())){
                String token= UserToken.createToken(user.getUserId());
                Response response=new Response(200,"Login Successfully",token);
                return response;
            }
            throw new UserException(405,"passWord not match");
        }
        throw new UserException(405,"need to verfiy token");
    }

    @Override
    public Response customerDetails(String token, AddressDto addressDto) {
        Long userID = UserToken.tokenVerify(token);
        System.out.println(userID);
        User user=userRepository.findById(userID)
                .orElseThrow(() -> new UserException(401, "User Id not found"));
        Address address = mapper.map(addressDto, Address.class);
        address.setUser(user);
        user.getAddressList().add(address);
        System.out.println(address.getUser().getUserId());
        addressRepository.save(address);
        userRepository.save(user);
        Response response1=new Response(200, environment.getProperty("customer.success.message"));
        return response1;
    }


    @Override
    public Response updateCustomerAddress(String token, Long addressId, AddressDto addressDto) {
        Long userID = UserToken.tokenVerify(token);
        User user=userRepository.findById(userID)
                .orElseThrow(() -> new BookException(401, "token.error"));
        Address address=addressRepository.findById(addressId).orElseThrow(() -> new BookException(401, "Address Id not Found"));
        address.setCity(addressDto.getCity());
        address.setAddress(addressDto.getAddress());
        address.setLandMark(addressDto.getLandMark());
        address.setLocality(addressDto.getLocality());
        address.setMobileNumber(addressDto.getMobileNumber());
        address.setPinCode(addressDto.getPinCode());
        address.setType(addressDto.getType());
        user.getAddressList().add(address);
        address.setUser(user);
        addressRepository.save(address);
        Response response1=new Response(200, environment.getProperty("customer.success.update.message"));
        return response1;
    }

    @Override
    public  List<Address> getData(String token) {
        Long userID = UserToken.tokenVerify(token);
        User user=userRepository.findById(userID)
                .orElseThrow(() -> new BookException(401, "token.error"));
        List<Address> addressList=user.getAddressList();
       // List<Address> address= (List<Address>) addressRepository.findAll();
        return addressList;
    }




}
