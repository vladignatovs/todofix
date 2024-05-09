package com.todo.todolist;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.todo.domain.Role;
import com.todo.domain.Task;
import com.todo.domain.Todolist;
import com.todo.repository.TaskRepository;
import com.todo.repository.TodolistRepository;
import com.todo.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/{id}")
public class UserIdController {
    
    UserRepository userRepository;
    TodolistRepository todolistRepository;
    TaskRepository taskRepository;

    public UserIdController(UserRepository userRepository, TodolistRepository todolistRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.todolistRepository = todolistRepository;
        this.taskRepository = taskRepository;
    }
    
    @GetMapping("")
    ModelAndView afterRegistrationPage(@PathVariable Long id, HttpSession session) {
        Long userId = (Long) session.getAttribute("user");
        ModelAndView modelAndView = new ModelAndView();
        if(!(userId != null && userId.equals(id))) {
            modelAndView.setViewName("redirect:/login");
            modelAndView.addObject("message", "ACCESS DENIED");
            return modelAndView;
        }

        if(userRepository.findUserById(userId).getRole() == Role.ADMIN) {
            List<Long> roles = new ArrayList<>();
            roles.add(id);
            modelAndView.addObject("roles", roles);
        }

        List<Todolist> allTodolists = (List<Todolist>) todolistRepository.findAll();
        List<Todolist> todolists = new ArrayList<>();
        for (Todolist todolist : allTodolists) {
            if(todolist.getUser().getId().equals(id)) {
                todolists.add(todolist);
            }
        }
        modelAndView.setViewName("registered");
        modelAndView.addObject("todolists", todolists);
        modelAndView.addObject("id", id);
        return modelAndView;
    }

    @GetMapping("/todolist/{listid}")
    ModelAndView todolist(@PathVariable Long id, @PathVariable Long listid, HttpSession session) {
        Long userId = (Long) session.getAttribute("user");
        if(!(userId != null 
        && userId.equals(id) 
        && todolistRepository.findTodolistById(listid) != null 
        && todolistRepository.findTodolistById(listid).getUser().getId().equals(id))) {
            return new ModelAndView("redirect:/login?message=ACCESS+DENIED");
        }

        List<Task> allTasks = (List<Task>) taskRepository.findAll();
        List<Task> tasks = new ArrayList<>();
        for (Task task : allTasks) {
            if(task.getTodolist().getId().equals(listid)) {
                tasks.add(task);
            }
        }
        ModelAndView modelAndView = new ModelAndView("todolist");
        modelAndView.addObject("listid", listid);
        modelAndView.addObject("tasks", tasks);
        return modelAndView;
    }
}
