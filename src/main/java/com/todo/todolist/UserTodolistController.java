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
    @RequestParam(value="addTime") String time ) {

        String result = time.replaceAll("[^\\d+]", "");
        int timerValue = 300;

        if(result != null) {
            if(result.length() <= 2) { 
                timerValue = Integer.parseInt(result.substring(0, result.length()));
            } else if (result.length() <= 4) {
                int minutes = Integer.parseInt(result.substring(0, result.length() - 2));
                int seconds = Integer.parseInt(result.substring(result.length() - 2, result.length()));
                timerValue =  seconds + minutes * 60;
            } else if (result.length() <= 6) {
                int hours = Integer.parseInt(result.substring(0, result.length() - 4));
                int minutes = Integer.parseInt(result.substring(result.length() - 4, result.length() - 2));
                int seconds = Integer.parseInt(result.substring(result.length() - 2, result.length()));
                timerValue =  seconds + minutes * 60 + hours * 3600;
            }
        }

        Task task = Task.builder()
        .title(input)
        .timer(timerValue)
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
