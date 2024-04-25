package com.todo.todolist;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.domain.Task;
import com.todo.domain.User;
import com.todo.repository.TaskRepository;

@RestController
public class jsUtility {
    
    TaskRepository taskRepository;

    public jsUtility(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/SAFKLJN-418A_ADF761")
    public List<Task> userList() {
        List<Task> tasks = new ArrayList<>();
        for(int i = 1; taskRepository.findTaskById(Long.valueOf(i)) != null; i++) {
            Task task = taskRepository.findTaskById((Long.valueOf(i)));
            tasks.add(task);
        }
        return tasks;
    }
}
