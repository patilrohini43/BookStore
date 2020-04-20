package com.bridgelabz.bookstore.service.Implementation;

import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.exception.BookException;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.model.Order;
import com.bridgelabz.bookstore.model.OrderBook;
import com.bridgelabz.bookstore.model.Response;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.service.IBookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService implements IBookService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    private Environment environment;

    @Override
    public Response addBook(BookDto bookDto) {
    Book bookExist= bookRepository.findByName(bookDto.getBookName());
    if(bookExist!=null){
        throw new BookException(400,environment.getProperty("book.error.message"));
    }
     Book book = mapper.map(bookDto, Book.class);
     bookRepository.save(book);
     Response response1=new Response(200, environment.getProperty("book.success.message"));
     return response1;
    }

    @Override
    public List<Book> getBookList() {
        List<Book> bookList=bookRepository.findAll();
        return bookList;
    }

    @Override
    public Book searchBooksByName(String bookName) {
      List<Book> bookList= bookRepository.findAll();
      Book result = bookList.stream().
               filter(book -> book.getBookName() != null)
              .filter(book -> book.getBookName().contains(bookName) || book.getBookName().equals(bookName))
              .findAny()
              .orElseThrow(()-> new BookException(400,"book not found"));
      return result;
    }

    @Override
    public Response deleteBook(Long bookId) {
       Book book= bookRepository.findById(bookId).orElseThrow(()->new BookException(400,"Book Id Not Found"));
       bookRepository.delete(book);
       Response response1=new Response(200, environment.getProperty("book.success.delete.message"));
       return response1;
    }

    @Override
    public Book getBookById(Long bookId) {
        Book book= bookRepository.findById(bookId).orElseThrow(()->new BookException(400,"Book Id Not Found"));
        return book;
    }

    @Override
    public Response addToCart(Long bookId) {
        Book book= bookRepository.findById(bookId).orElseThrow(()->new BookException(400,"Book Id Not Found"));
        book.setCartStatus(true);
        bookRepository.save(book);
        Response response1=new Response(200, environment.getProperty("book.success.cart.message"));
        return response1;
    }

    @Override
    public List<Book> getAllCartList() {
        List<Book> cartBook = bookRepository.findAll().stream().filter(data -> data.isCartStatus() == true).collect(Collectors.toList());
        return cartBook;
    }

    @Override
    public Response removeFromCart(Long bookId) {
        Book book= bookRepository.findById(bookId).orElseThrow(()->new BookException(400,"Book Id Not Found"));
        book.setCartStatus(false);
        bookRepository.save(book);
        Response response1=new Response(200, environment.getProperty("book.remove.cart.message"));
        return response1;
    }

}
