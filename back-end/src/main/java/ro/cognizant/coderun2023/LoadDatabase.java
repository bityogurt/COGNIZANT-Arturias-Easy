package ro.cognizant.coderun2023;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.cognizant.coderun2023.Book;
import ro.cognizant.coderun2023.BookRepository;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(BookRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Book("The Lord of the Rings","J.R.R. Tolkien","George Allen & Unwin")));
            log.info("Preloading " + repository.save(new Book("The Ring of the Lords","Brr Jokien","George Allen & Unwin")));
            log.info("Preloading " + repository.save(new Book("Digital Fortress","Dan Brown","St. Martin's Press")));
        };
    }
}
