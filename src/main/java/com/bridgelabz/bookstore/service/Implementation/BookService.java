package com.bridgelabz.bookstore.service.Implementation;

import com.bridgelabz.bookstore.dto.BookDto;
import com.bridgelabz.bookstore.enumrator.SortEnum;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static java.lang.Math.toIntExact;

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
    public Page<Book> getBookList(Pageable pageable) {
        Page<Book> bookList=bookRepository.findAll(pageable);
        return bookList;
    }

    @Override
    public List<Resource> getBookListImages() {
//        List<Resource> imageList=new ArrayList<>();
//        Page<Book> bookList=bookRepository.findAll();
//        for (Book book:bookList){
//            String fileName=book.getImage();
//            Resource image=this.getImages(fileName);
//            imageList.add(image);
//        }
        return null;
    }

    @Override
    public Resource getBookImages(Long bookId) {
        Book book= bookRepository.findById(bookId).orElseThrow(()->new BookException(400,"Book Id Not Found"));
        return getImages(book.getImage());
    }

    @Override
    public Page<Book> sort(SortEnum sortEnum,int offset,int limit) {
        List<Book> bookList= bookRepository.findAll();
        List<Book> sortList = sortEnum.sortByValue(bookList);
        Page<Book> bookSearchList = convetToPage(offset, limit, sortList);
        return bookSearchList;
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
    public Page<Book> searchBooksByName(String bookName,int offset,int limit,SortEnum sortEnum) {
      List<Book> bookList= bookRepository.findAll();
      List<Book> searchList = bookList.stream().
               filter(book -> book.getBookName() != null)
              .filter(book -> book.getBookName().contains(bookName) || book.getBookName().equals(bookName))
              .collect(Collectors.toList());
        if(searchList.isEmpty()){
            throw new BookException(400,"book not found");
        }
      List<Book> filtersortList=sortEnum.sortByValue(searchList);
        Page<Book> bookSearchList = convetToPage(offset, limit, filtersortList);
        return bookSearchList;
    }


    private Page<Book> convetToPage(int offset,int limit,List<Book> result){
        PageRequest pageRequest = PageRequest.of(offset, limit);
        int total = result.size();
        int start = toIntExact(pageRequest.getOffset());
        int end = Math.min((start + pageRequest.getPageSize()), total);
        List<Book> output = new ArrayList<>();
        if (start <= end) {
            output = result.subList(start, end);
        }
        return new PageImpl<>(
                output,
                pageRequest,
                total
        );
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
