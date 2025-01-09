package io.sc0.REST.API.Demo.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "book")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int bookId;

    @Column(name = "book_name", nullable = false)
    private String bookName;

    @Column(name = "author", nullable = false)
    private String author;

    // Custom Constructor for Sanitization or Partial Initialization
    public Book(String author, String bookName) {
        this.author = author;
        this.bookName = bookName;
    }
}
