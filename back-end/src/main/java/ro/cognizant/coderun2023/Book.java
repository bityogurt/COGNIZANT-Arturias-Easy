package ro.cognizant.coderun2023;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "book")

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    @Column(name = "bookName")
    private String bookName;

    @Column(name = "author")
    private String author;

    @Column(name = "publishers")
    private String publishers;

    public String toString(){
        return "Book{ bookName = " + this.bookName + ", author = " + this.author + ", publishers = " + this.publishers + "}";
    }

}
