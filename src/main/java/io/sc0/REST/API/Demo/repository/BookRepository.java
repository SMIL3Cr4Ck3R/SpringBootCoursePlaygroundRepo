package io.sc0.REST.API.Demo.repository;

import io.sc0.REST.API.Demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book,Integer> {

}
