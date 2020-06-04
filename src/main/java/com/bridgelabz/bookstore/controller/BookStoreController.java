package com.bridgelabz.bookstore.controller;


import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.enumrator.SortEnum;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.model.Response;
import com.bridgelabz.bookstore.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
@CrossOrigin(origins="http://localhost:3000")
public class BookStoreController {

    @Autowired
    IBookService iBookService;

    @GetMapping("/get")
    public String getBookName(){
        return "ReactJs";
    }

    @PostMapping(value = "/addBook")
    public ResponseEntity<Response> addBook(@RequestBody BookDto bookDto,@RequestParam("File") MultipartFile file) throws IOException {
        Response response= iBookService.addBook(bookDto,file);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/bookList")
    public Page<Book> getBookList(Pageable pageable){
        return iBookService.getBookList(pageable);
    }

    @GetMapping("/bookListImages")
    public List<Resource> getBookListImages(){
        return iBookService.getBookListImages();
    }

    @GetMapping("/bookListImages/{bookId}")
    public Resource getBookListImages(@PathVariable(name = "bookId") Long bookId){
        iBookService.getBookImages(bookId);
        return   iBookService.getBookImages(bookId);
    }

    @GetMapping("/searchBook/{bookName}/{offset}/{limit}/{sortValue}")
    public  Page<Book> searchBooksByName(@PathVariable(name ="bookName") String bookName,@PathVariable(name="offset") int offset,@PathVariable(name="limit") int limit,@PathVariable(name="sortValue") SortEnum sortEnum)
    {
        return iBookService.searchBooksByName(bookName,offset,limit,sortEnum);
    }

    @GetMapping("/getBookId/{bookId}")
    public Book getBookById(@PathVariable(name = "bookId") Long bookId){
        Book bookList= iBookService.getBookById(bookId);
        return bookList;
    }

    @GetMapping("/sort/{sortValue}")
    public Page<Book> sort(@PathVariable(name="sortValue") SortEnum sortEnum,@RequestParam  int offset,@RequestParam  int limit){
        Page<Book> sortList= iBookService.sort(sortEnum,offset,limit);
        return sortList;
    }


    @DeleteMapping("/deleteBook/{bookId}")
    public ResponseEntity<Response> deleteBook(@PathVariable(name = "bookId") Long bookId) {
        Response response= iBookService.deleteBook(bookId);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
