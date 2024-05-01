package com.todo.todolist;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.todo.domain.Task;
import com.todo.domain.Todolist;
import com.todo.domain.User;
import com.todo.repository.TaskRepository;
import com.todo.repository.TodolistRepository;
import com.todo.repository.UserRepository;

import jakarta.servlet.http.HttpSession;


@RestController
public class UserRestController {
    
    UserRepository userRepository;
    TodolistRepository todolistRepository;
    TaskRepository taskRepository;

    public UserRestController(UserRepository userRepository, TodolistRepository todolistRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.todolistRepository = todolistRepository;
        this.taskRepository = taskRepository;
    }

    @GetMapping(value = "/addUser")
    void addUser() {
        User user = User.builder()
            .email("test@test.com")
            .password("test123")
            .build();

        userRepository.save(user);
    }
    
    @GetMapping(value = "/addTodolist")
    ModelAndView addTodolist(@RequestParam(value="id") Long user_id) {
        Todolist todolist = Todolist.builder() 
            .title("Test")
            .user(userRepository.findUserById(user_id))
            .build();
        todolistRepository.save(todolist);
        ModelAndView modelAndView = new ModelAndView(String.format("redirect:/user/%s/todolist/%s", user_id, todolist.getId()));
        // modelAndView.addObject("message", "CREATED");
        return modelAndView;
    }

    @GetMapping("/dashboard")
    public List<User> dashboard(HttpSession session) {
        List<User> users = new ArrayList<>();
        Long userId = (Long) session.getAttribute("user");
        if (userId != null) {
            User user = userRepository.findById(userId).orElse(null);
            if (user != null) {
                // Use the user object as needed
                users.add(user);
                return users;
            } else {
                // Handle the case when the user is not found
                return users;
            }
        } else {
            // Handle the case when the user is not logged in
            return users;
        }
    }   
}
