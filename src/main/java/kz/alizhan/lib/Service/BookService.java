package kz.alizhan.lib.Service;

import kz.alizhan.lib.Entity.Book;

import java.util.List;


public interface BookService {


    List<Book> getBooksByAuthor(String author);

    List<Book> getBooksByYearOfIssue(Integer year);

    List<Book> getBooksByName(String name);

}
