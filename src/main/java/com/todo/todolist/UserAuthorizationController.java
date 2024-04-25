package com.todo.todolist;

import java.net.http.HttpRequest;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.todo.domain.Role;
import com.todo.domain.User;
import com.todo.repository.UserRepository;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;


@Controller 
public class UserAuthorizationController {
    
    UserRepository userRepository;

    public UserAuthorizationController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/register")
    ModelAndView registerTest(@RequestParam(value="message") String message) {
        ModelAndView modelAndView = new ModelAndView("register");
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @GetMapping("/login")
    ModelAndView loginUser(@RequestParam(value="message") String message) {
        ModelAndView modelAndView = new ModelAndView("login");
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @GetMapping("/loginUser")
    ModelAndView loginUser(@RequestParam(value="email") String email,
    @RequestParam(value="password") String password, HttpSession session) {
        String message;
        ModelAndView modelAndView = new ModelAndView();
        if(userRepository.findByEmail(email) == null) {
            message = "No such user exists.";
            modelAndView.addObject("message", message);
            modelAndView.setViewName("redirect:/login");
            return modelAndView;
        } else if(!userRepository.findByEmail(email).getPassword().equals(password)) {
            message = "Email or password is incorrect.";
            modelAndView.addObject("message", message);
            modelAndView.setViewName("redirect:/login");
            return modelAndView;
        }
        Long id = userRepository.findByEmail(email).getId();

        session.setAttribute("user", id);

        modelAndView.setViewName(String.format("redirect:/user/%s", id));
        return modelAndView;
    }

    @GetMapping("/registerUser")
    ModelAndView registerUser(@RequestParam(value="email") String email, 
    @RequestParam(value="password") String password,
    @RequestParam(value="psw-repeat") String repeat, HttpSession session) {

        ModelAndView modelAndView = new ModelAndView();
        Role role;

        if(userRepository.findByEmail(email) != null) {

            modelAndView.addObject("message", "This email is already taken.");
            modelAndView.setViewName("redirect:/register");
            return modelAndView;

        } else if(!password.equals(repeat)) {

            modelAndView.addObject("message", "The passwords are different from each other.");
            modelAndView.setViewName("redirect:/register");
            return modelAndView;

        }

        if(email.equals("admin@admin.com")) {
            role = Role.ADMIN;
        } else {
            role = Role.USER;
        }

        User user = User.builder()
        .email(email)
        .password(password)
        .role(role)
        .build();

        userRepository.save(user);
        Long id = userRepository.findByEmail(email).getId();

        session.setAttribute("user", id);
        
        modelAndView.setViewName(String.format("redirect:/user/%s", id));
        return modelAndView;

    }



    @GetMapping("/registerDone")
    ModelAndView registration(@RequestParam(value = "registered") String email) {
        User user = userRepository.findByEmail(email);
        ModelAndView modelAndView = new ModelAndView("viewUser");
        modelAndView.addObject("email", user.getEmail());
        modelAndView.addObject("password", user.getPassword());
        return modelAndView;
    }

    @GetMapping("/log-out")
    ModelAndView logOut(HttpSession session) {
        session.removeAttribute("user");
        return new ModelAndView("redirect:/login?message=");
    }
}
