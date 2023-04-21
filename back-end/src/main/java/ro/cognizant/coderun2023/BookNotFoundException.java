package ro.cognizant.coderun2023;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(String name) {
        super("Could not find book " + name);
    }
}
