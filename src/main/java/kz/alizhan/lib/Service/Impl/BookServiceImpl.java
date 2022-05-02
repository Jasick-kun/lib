package kz.alizhan.lib.Service.Impl;

import kz.alizhan.lib.Entity.Book;
import kz.alizhan.lib.Repository.BookRepository;
import kz.alizhan.lib.Service.BookService;
import kz.alizhan.lib.Service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final LocationService locationService;
    private final BookRepository bookRepository;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).get();
    }

    @Override
    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findBooksByAuthor(author);
    }

    @Override
    public List<Book> getBooksByName(String name) {
        return bookRepository.findBooksByName(name);
    }

    @Override
    public List<Book> getBooksByYearOfIssue(Integer year) {
        return bookRepository.findBooksByYearOfIssue(year);
    }

    @Override
    public void addBook(Book book) {
        book.setLocation(locationService.addLocation(book.getLocation()));
        book.setId(null);
        bookRepository.addBook(book.getYearOfIssue(), book.getName(), book.getAuthor(), book.getLocation().getId());
    }
}
