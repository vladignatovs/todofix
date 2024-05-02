package com.todo.todolist;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.domain.Task;
import com.todo.repository.TaskRepository;

@RestController
public class jsUtility {
    
    TaskRepository taskRepository;

    public jsUtility(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/SAFKLJN-418A_ADF761")
    public List<Task> userList() {
        return (List<Task>) taskRepository.findAll();
    }
}
