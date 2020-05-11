package com.bridgelabz.bookstore.service.Implementation;

import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.exception.BookException;
import com.bridgelabz.bookstore.exception.UploadException;
import com.bridgelabz.bookstore.model.Book;
import com.bridgelabz.bookstore.model.Response;
import com.bridgelabz.bookstore.repository.BookRepository;
import com.bridgelabz.bookstore.service.IBookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import java.util.stream.Collectors;

@Service
public class BookService implements IBookService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    private Environment environment;

    private final Path pathlocation= Paths.get("/home/rohini/Videos/BookStore/src/main/resources/image");

    @Override
    public Response addBook(BookDto bookDto, MultipartFile file) throws IOException {
    Book bookExist= bookRepository.findByName(bookDto.getBookName());
    if(bookExist!=null){
        throw new BookException(400,environment.getProperty("book.error.message"));
    }
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid.toString());
        String image = uuid.toString();
        Files.copy(file.getInputStream(), this.pathlocation.resolve(image),
                StandardCopyOption.REPLACE_EXISTING);
     Book book = mapper.map(bookDto, Book.class);
     book.setImage(image);
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
    public List<Resource> getBookListImages() {
        List<Resource> imageList=new ArrayList<>();
        List<Book> bookList=bookRepository.findAll();
        for (Book book:bookList){
            String fileName=book.getImage();
            Resource image=this.getImages(fileName);
            imageList.add(image);
        }
        return imageList;
    }

    @Override
    public Resource getBookImages(Long bookId) {
        Book book= bookRepository.findById(bookId).orElseThrow(()->new BookException(400,"Book Id Not Found"));
        return getImages(book.getImage());
    }


    public Resource getImages(String fileName){
        Path file = this.pathlocation.resolve(fileName);
        try {
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                System.out.println(resource);
                return resource;
            } else {
                throw new UploadException(
                        "Could not read file: " + fileName);
            }
        } catch (MalformedURLException e) {
            throw new UploadException("Could not read file: " + fileName, e);
        }
    }

    @Override
    public List<Book> searchBooksByName(String bookName) {
      List<Book> bookList= bookRepository.findAll();
      List<Book> result = bookList.stream().
               filter(book -> book.getBookName() != null)
              .filter(book -> book.getBookName().contains(bookName) || book.getBookName().equals(bookName))
              .collect(Collectors.toList());
             // .orElseThrow(()-> new BookException(400,"book not found"));
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

}
