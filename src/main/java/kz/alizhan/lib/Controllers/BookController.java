package kz.alizhan.lib.Controllers;

import kz.alizhan.lib.Entity.Book;
import kz.alizhan.lib.Service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class BookController {


    private final BookService bookService;


    @GetMapping("/")
    @ResponseBody
    public HttpEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<List<Book>>(bookService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/name")
    public List<Book> getBooksByName(@RequestParam String name) {
        return bookService.getBooksByName(name);
    }

    @GetMapping("/author")
    public List<Book> getBooksByAuthor(@RequestParam String author) {
        return bookService.getBooksByAuthor(author);
    }

    @GetMapping("/year")
    public List<Book> getBooksByYearOfIssue(@RequestParam Integer year) {
        return bookService.getBooksByYearOfIssue(year);
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.getBookById(id);
    }

    @PostMapping("/")
    public HttpStatus addBook(@RequestBody Book book) {
        bookService.addBook(book);
        return HttpStatus.OK;
    }

}
