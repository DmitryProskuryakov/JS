package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/app/admin")
public class AdminRESTController {
    private final UserService userServiceImpl;
    private final RoleService roleServiceImpl;

    public AdminRESTController(UserServiceImpl userServiceImpl, RoleService roleServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.roleServiceImpl = roleServiceImpl;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) Integer id) {
        if (id != null) {
            List<User> listUser = new ArrayList<>();
            listUser.add(userServiceImpl.findOne(id).get());
            return new ResponseEntity<>(listUser, HttpStatus.OK);
        }
        List<User> listUser = userServiceImpl.findAll();
        return new ResponseEntity<>(listUser, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUser(@RequestParam(required = false) Integer id) {
            return new ResponseEntity<>(userServiceImpl.findOne(id).get(), HttpStatus.OK);
    }

    @GetMapping("/currentUser")
    public ResponseEntity<User> getCurrentUser(@AuthenticationPrincipal User user) {
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addUser(@RequestBody User user) {
        userServiceImpl.save(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deleteUser(@RequestParam(required = false) Integer id) {
        userServiceImpl.delete(id);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping("/edit")
    public ResponseEntity<HttpStatus> editUser(@RequestBody User user, @RequestParam(required = false) Integer id) {
        userServiceImpl.update(id, user);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping(value = "/getRoles")
    public ResponseEntity<Collection<Role>> getAllRoles() {
        return ResponseEntity.ok(roleServiceImpl.getRoleSet());
    }
}
