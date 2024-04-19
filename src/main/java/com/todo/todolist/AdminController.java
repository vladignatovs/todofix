package com.todo.todolist;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.todo.domain.User;
import com.todo.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    UserRepository userRepository;

    public AdminController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/userList")
    ModelAndView listOfUsers() {
        List<User> users = new ArrayList<>();
        ModelAndView modelAndView = new ModelAndView("userList");
        for(int i = 1; userRepository.findUserById(Long.valueOf(i)) != null; i++) {
            User user = userRepository.findUserById((Long.valueOf(i)));
            users.add(user);
        }
        modelAndView.addObject("users", users);
        return modelAndView;
    }
}
