package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class AuthorizedUser {
    private UserService userServiceImpl;

    @Autowired
    public AuthorizedUser(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @RequestMapping
    public String getUserPage(Model model, Principal principal) {
        String username = principal.getName();
        User user = userServiceImpl.findByName(username);

        model.addAttribute("user", user);
        return "user";
    }
}
