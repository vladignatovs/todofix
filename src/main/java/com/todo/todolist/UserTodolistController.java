package com.todo.todolist;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.todo.domain.Task;
import com.todo.repository.TaskRepository;
import com.todo.repository.TodolistRepository;
import com.todo.repository.UserRepository;

@Controller
public class UserTodolistController {
    
    UserRepository userRepository;
    TodolistRepository todolistRepository;
    TaskRepository taskRepository;

    public UserTodolistController(UserRepository userRepository, TodolistRepository todolistRepository, TaskRepository taskRepository) {
        this.userRepository = userRepository;
        this.todolistRepository = todolistRepository;
        this.taskRepository = taskRepository;
    }

    @GetMapping(value = "/addTask")
    ModelAndView addTask(
    @RequestParam(value="listid") Long todolist_id, 
    @RequestParam(value="addInput") String input,
    @RequestParam(value="addTime") int time ) {

        Task task = Task.builder()
        .title(input)
        .timer(time)
        .todolist(todolistRepository.findTodolistById(todolist_id))
        .build();

        taskRepository.save(task);
        ModelAndView modelAndView = new ModelAndView(
            String.format(
                "redirect:/user/%s/todolist/%s", todolistRepository.findTodolistById(todolist_id).getUser().getId(), 
                todolist_id));
                
        return modelAndView;
    }


    @GetMapping("/deleteTask")
    ModelAndView deleteTask(@RequestParam(value="taskid") Long task_id) {
        ModelAndView modelAndView = new ModelAndView(String.format(
            "redirect:/user/%s/todolist/%s",
            taskRepository.findTaskById(task_id).getTodolist().getUser().getId(),
            taskRepository.findTaskById(task_id).getTodolist().getId()));
        taskRepository.deleteById(task_id);
        return modelAndView;
    }

}
