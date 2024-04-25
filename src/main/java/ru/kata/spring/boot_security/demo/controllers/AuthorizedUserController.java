package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import java.security.Principal;

@RestController
@RequestMapping("/app/user")
public class AuthorizedUserController {
    private final UserService userServiceImpl;

    public AuthorizedUserController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping
    public ResponseEntity<User> getUserPage(Principal principal) {
        String username = principal.getName();
        User user = userServiceImpl.findByName(username);

        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
