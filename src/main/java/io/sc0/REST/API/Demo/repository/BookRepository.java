package io.sc0.REST.API.Demo.repository;

import io.sc0.REST.API.Demo.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// @RepositoryRestResource(path = "books")
public interface BookRepository extends JpaRepository<Book,Integer> {

}
