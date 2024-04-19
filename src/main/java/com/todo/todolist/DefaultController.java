package com.todo.todolist;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DefaultController {

    @GetMapping("")
    ModelAndView registerRedirect() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/register?message=");
        return modelAndView;
    }
}