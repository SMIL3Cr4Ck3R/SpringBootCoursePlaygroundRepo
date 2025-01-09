package io.sc0.REST.API.Demo.service;

import io.sc0.REST.API.Demo.Exception.NotFoundException;
import io.sc0.REST.API.Demo.entity.Book;
import io.sc0.REST.API.Demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements SimpleCrudService<Book> {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book findOne(Book object) {
        return bookRepository.findOne(Example.of(object))
                .orElseThrow(() -> new NotFoundException("Book not found"));
    }

    @Override
    public Book findOneById(int objectId) {
        return bookRepository.findById(objectId)
                .orElseThrow(() -> new NotFoundException("Book with ID: " + objectId + " not found"));
    }

    @Override
    public List<Book> findAll() {
        List<Book> listOfBooks = bookRepository.findAll();
        if (listOfBooks.isEmpty()) {
            throw new NotFoundException("No books found");
        }
        return listOfBooks;
    }

    @Override
    public Book createOne(Book object) {
        // Sanitize and save the book
        Book sanitizedBook = new Book(object.getAuthor(), object.getBookName());
        return bookRepository.save(sanitizedBook);
    }

    @Override
    public List<Book> createMany(List<Book> collection) {
        if (collection.isEmpty()) {
            throw new IllegalArgumentException("Collection cannot be empty");
        }
        return bookRepository.saveAll(collection);
    }

    @Override
    public Book updateOne(int bookId) {
        return null;
    }

    @Override
    public Book updateOne(int bookId, Book updatedBook) {
        // Find existing book
        Book existingBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFoundException("Book with ID: " + bookId + " not found"));

        // Update fields
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setBookName(updatedBook.getBookName());

        // Save updated book
        return bookRepository.save(existingBook);
    }

    @Override
    public List<Book> updateMany(List<Book> collection) {
        if (collection.isEmpty()) {
            throw new IllegalArgumentException("Collection cannot be empty");
        }
        return bookRepository.saveAll(collection);
    }

    @Override
    public void deleteOne(int id) {
        // Check if the book exists before deleting
        if (!bookRepository.existsById(id)) {
            throw new NotFoundException("Book with ID: " + id + " not found");
        }
        bookRepository.deleteById(id);
    }

    @Override
    public void deleteMany(int[] collectionId) {
        for (int id : collectionId) {
            deleteOne(id);
        }
    }
}
