package ro.cognizant.coderun2023;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    private final BookRepository repository;

    BookController(BookRepository repository){
        this.repository = repository;
    }

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/books")
    List<Book> all() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/Books")
    Book newBook(@RequestBody Book newBook) {
        return repository.save(newBook);
    }

    // Single item

    @GetMapping("/Books/{bookName}")
    Book one(@PathVariable String bookName) {

        return repository.findById(bookName)
                .orElseThrow(() -> new BookNotFoundException(bookName));
    }

    @PutMapping("/Books/{bookName}")
    Book replaceBook(@RequestBody Book newBook, @PathVariable String bookName) {

        return repository.findById(bookName)
                .map(Book -> {
                    Book.setBookName(newBook.getBookName());
                    Book.setAuthor(newBook.getAuthor());
                    Book.setPublishers(newBook.getPublishers());
                    return repository.save(Book);
                })
                .orElseGet(() -> {
                    newBook.setBookName(bookName);
                    return repository.save(newBook);
                });
    }

    @DeleteMapping("/Books/{bookName}")
    void deleteBook(@PathVariable String bookName) {
        repository.deleteById(bookName);
    }
}
