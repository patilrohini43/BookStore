package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.model.Response;

import java.util.List;

public interface IBookService {

    Response addBook(BookDto bookDto);
    List<Book> getBookList();
    Book searchBooksByName(String bookName);
    Response deleteBook(Long bookId);
    Book getBookById(Long bookId);

    Response addToCart(Long bookId);

    List<Book> getAllCartList();

    Response removeFromCart(Long bookId);
}
