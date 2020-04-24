package com.bridgelabz.bookstore.controller;


import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.model.Response;
import com.bridgelabz.bookstore.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@PropertySource("classpath:message.properties")
@RequestMapping(value = "/bookname")
public class BookStoreController {

    @Autowired
    IBookService iBookService;

    @GetMapping("/get")
    public String getBookName(){
        return "ReactJs";
    }

    @PostMapping(value = "/addBook")
    public ResponseEntity<Response> addBook(@ModelAttribute BookDto bookDto,@RequestParam("File") MultipartFile file) throws IOException {
        Response response= iBookService.addBook(bookDto,file);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/bookList")
    public List<Book> getBookList(){
        return iBookService.getBookList();
    }

    @GetMapping("/bookListImages")
    public List<Resource> getBookListImages(){
        return iBookService.getBookListImages();
    }


    @GetMapping("/searchBook/{bookName}")
    public Book searchBooksByName(@PathVariable(name ="bookName") String bookName)
    {
        return iBookService.searchBooksByName(bookName);
    }

    @GetMapping("/getBookId/{bookId}")
    public Book getBookById(@PathVariable(name = "bookId") Long bookId){
        Book bookList= iBookService.getBookById(bookId);
        return bookList;
    }

    @PutMapping("/addtoCart/{bookId}")
    public ResponseEntity<Response> addToCart(@PathVariable(name = "bookId") Long bookId) {
        Response response= iBookService.addToCart(bookId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("/deleteFromCart/{bookId}")
    public ResponseEntity<Response> removeFromCart(@PathVariable(name = "bookId") Long bookId) {
        Response response= iBookService.removeFromCart(bookId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/getAllCart")
    public List<Book> getAllCartList() {
        return iBookService.getAllCartList();
    }

    @DeleteMapping("/deleteBook/{bookId}")
    public ResponseEntity<Response> deleteBook(@PathVariable(name = "bookId") Long bookId) {
        Response response= iBookService.deleteBook(bookId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
