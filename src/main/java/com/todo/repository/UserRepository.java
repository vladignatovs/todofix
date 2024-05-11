package com.todo.repository;

import org.springframework.data.repository.CrudRepository;

import com.todo.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

    User findUserById(Long id);
}