package com.bridgelabz.bookstore.service.Implementation;

import com.bridgelabz.bookstore.dto.UserDto;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.model.Response;
import com.bridgelabz.bookstore.model.User;
import com.bridgelabz.bookstore.repository.UserRepository;
import com.bridgelabz.bookstore.service.IUserService;
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
    private Environment environment;


    @Override
    public Response registerUser(UserDto userDto) {
        User user = mapper.map(userDto, User.class);
        userRepository.save(user);
        Response response=new Response(200, environment.getProperty("book.success.message"));
        return response;
    }
}
