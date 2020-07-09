package com.br.knowledge.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.br.knowledge.model.User;

public interface UserRepository extends MongoRepository<User, Integer>{

	User findByUsername(String userName);

}
