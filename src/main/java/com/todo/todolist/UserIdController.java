package com.todo.todolist;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.todo.repository.TodolistRepository;
import com.todo.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/{id}")
public class UserIdController {
    
    UserRepository userRepository;
    TodolistRepository todolistRepository;

    public UserIdController(UserRepository userRepository, TodolistRepository todolistRepository) {
        this.userRepository = userRepository;
        this.todolistRepository = todolistRepository;
    }
    
    @GetMapping("")
    ModelAndView afterRegistrationPage(@PathVariable Long id, HttpSession session) {
        Long userId = (Long) session.getAttribute("user");
        if(userId != null && userId.equals(id)) {
            ModelAndView modelAndView = new ModelAndView("registered");
            modelAndView.addObject("id", id);
            return modelAndView;
        }

        ModelAndView modelAndView = new ModelAndView("redirect:/login");
        modelAndView.addObject("message", "ACCESS DENIED");
        return modelAndView;
        // HttpRequest request = new HttpRequest(HttpMethod.GET, "/user/{id}");
        
        // session = request.getSession();
        // session.invalidate();
        // User user = (User) session.getAttribute("user");

        // if (user.getId() != id) {
        // }
        // ModelAndView modelAndView = new ModelAndView("viewUser");
        // if(userRepository.findById(id) != null) {
        //     User user = userRepository.findUserById(id);
        //     modelAndView.addObject("email", user.getEmail());
        //     modelAndView.addObject("password", user.getPassword());
        // }
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

        ModelAndView modelAndView = new ModelAndView("todolist");
        modelAndView.addObject("id", id);
        modelAndView.addObject("listid", listid);
        return modelAndView;
    }
}
