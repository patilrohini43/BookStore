package com.bridgelabz.bookstore.enumrator;

import com.bridgelabz.bookstore.model.Book;
import javafx.print.Collation;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public enum SortEnum {

    LowToHigh{
        @Override
        public List<Book> sortByValue(List<Book> bookList) {
            List<Book> sortList= bookList.stream().sorted(Comparator.comparing(book -> book.getPrice())).collect(Collectors.toList());
            return sortList;
        }
        
    },
    HighToLow{
        @Override
        public List<Book> sortByValue(List<Book> bookList) {
            List<Book> sortList= bookList.stream().sorted(Comparator.comparing(book -> book.getPrice())).collect(Collectors.toList());
            Collections.reverse(sortList);
            return sortList;
        }
    },
    unSorted{
        @Override
        public List<Book> sortByValue(List<Book> bookList) {
            List<Book> sortList= bookList.stream().collect(Collectors.toList());
            return sortList;
        }
    },

    ;
    public abstract  List<Book> sortByValue(List<Book> bookList);

}
