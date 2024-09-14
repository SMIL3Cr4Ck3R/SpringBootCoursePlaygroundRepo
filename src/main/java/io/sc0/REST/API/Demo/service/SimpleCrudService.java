package io.sc0.REST.API.Demo.service;

import io.sc0.REST.API.Demo.entity.Book;

import java.util.List;
import java.util.Optional;

public interface SimpleCrudService<T> {

    T findOne(T object);
    T findOneById(int objectId);
    List<T> findAll();

    T createOne(T object);
    List<T> createMany(List<T> collection);

    T updateOne(int bookId);
    List<T> updateMany(List<T> collection);

    void deleteOne(int id);
    void deleteMany(int[] collectionId);

//    Optional<T> findOne(T object);
//
//    Optional<T> findOneById(int objectId);
//    Optional<List<T>> findAll();
//
//    Optional<T> createOne(T object);
//    Optional<List<T>> createMany(List<T> collection);
//
//    Optional<T> updateOne(int bookId);
//    Optional<List<T>> updateMany(List<T> collection);
//
//    void deleteOne(int id);
//    void deleteMany(int[] collectionId);

}
