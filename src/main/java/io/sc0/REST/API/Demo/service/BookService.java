package io.sc0.REST.API.Demo.service;


import io.sc0.REST.API.Demo.Exception.NotFoundException;
import io.sc0.REST.API.Demo.entity.Book;
import io.sc0.REST.API.Demo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements SimpleCrudService<Book> {


    private final BookRepository bookRepository;

    @Autowired
    public BookService (BookRepository bookRepository) {
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
                .orElseThrow(() -> new NotFoundException("Book with ID: " + objectId + "Not Found"));
    }

    @Override
    public List<Book> findAll() {

        Optional<List<Book>> listOfBooks = Optional.of(bookRepository.findAll());
        return listOfBooks.orElseThrow(() -> new NotFoundException("List of books not found"));
    }

    @Override
    public Book createOne(Book object) {

        Book sanitizedBook = new Book(object.getAuthor(), object.getBookName());

        Optional<Book> createdBook = Optional.of(bookRepository.save(sanitizedBook));

        return createdBook.orElseThrow(RuntimeException::new);

    }

    @Override
    public List<Book> createMany(List<Book> collection) {
        return List.of();
    }

    @Override
    public Book updateOne(int bookId) {
        return null;
    }

    @Override
    public List<Book> updateMany(List<Book> collection) {
        return List.of();
    }

    @Override
    public void deleteOne(int id) {

    }

    @Override
    public void deleteMany(int[] collectionId) {

    }


}
