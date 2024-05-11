package com.todo.repository;

import org.springframework.data.repository.CrudRepository;

import com.todo.domain.Todolist;

public interface TodolistRepository extends CrudRepository<Todolist, Long> {
    
    Todolist findTodolistById(Long id);
}
