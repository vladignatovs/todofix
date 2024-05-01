package com.todo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.todo.domain.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {
    
    Task findTaskById(Long id);
    
}
