package com.bridgelabz.bookstore;


import com.bridgelabz.bookstore.controller.BookStoreController;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.bridgelabz.bookstore.controller.DemoController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


@RunWith(SpringRunner.class)
@WebAppConfiguration
@WebMvcTest(DemoController.class)
public class BookController {

    @Autowired
    private MockMvc mockMvc;
    @Test
    public void helloWorld_basic() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/app/get")
                .accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(content().string("ReactJs"))
                .andReturn();
        Assert.assertEquals("ReactJs", result.getResponse().getContentAsString());
    }
}
