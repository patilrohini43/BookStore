package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.model.Response;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IBookService {

    Response addBook(BookDto bookDto, MultipartFile file) throws IOException;
    List<Book> getBookList();
    Book searchBooksByName(String bookName);
    Response deleteBook(Long bookId);
    Book getBookById(Long bookId);
    Response addToCart(Long bookId);
    List<Book> getAllCartList();
    Response removeFromCart(Long bookId);

    List<Resource> getBookListImages();
}
