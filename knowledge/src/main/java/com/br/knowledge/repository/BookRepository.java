package com.br.knowledge.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.br.knowledge.model.Book;

public interface BookRepository extends MongoRepository<Book, String>{

}
