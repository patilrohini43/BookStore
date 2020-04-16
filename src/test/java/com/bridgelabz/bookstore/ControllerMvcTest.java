package com.bridgelabz.bookstore;

import com.bridgelabz.bookstore.controller.BookStoreController;
import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.model.Book;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc //need this in Spring Boot test
public class ControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getName() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/bookname/get")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("ReactJs"))
                .andReturn();
        Assert.assertEquals("ReactJs", result.getResponse().getContentAsString());
    }

    @Test
    public void getList() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/bookname/bookList")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andReturn();
        Assert.assertEquals(result.getResponse().getContentAsString(),result.getResponse().getContentAsString());
    }
}

