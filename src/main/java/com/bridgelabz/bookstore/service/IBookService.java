package com.bridgelabz.bookstore.service;

import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.enumrator.SortEnum;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.model.Response;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IBookService {

    Response addBook(BookDto bookDto, MultipartFile file) throws IOException;
    Page<Book> getBookList(Pageable pageable);
    Page<Book> searchBooksByName(String bookName,int offset,int limit,SortEnum sortEnum);
    Response deleteBook(Long bookId);
    Book getBookById(Long bookId);
    List<Resource> getBookListImages();
    Resource getBookImages(Long bookId);

    Page<Book> sort(SortEnum sortEnum,int offset,int limit);
}
