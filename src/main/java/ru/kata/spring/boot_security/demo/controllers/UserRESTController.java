package ru.kata.spring.boot_security.demo.controllers;

import com.fasterxml.jackson.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping("/admin")
public class UserRESTController {
    private final UserService userServiceImpl;
    private final RoleService roleServiceImpl;

    public UserRESTController(UserServiceImpl userServiceImpl, RoleService roleServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.roleServiceImpl = roleServiceImpl;
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers(@RequestParam(required = false) Integer id) {
        if (id != null) {
            List<User> listUser = new ArrayList<>();
            listUser.add(userServiceImpl.findOne(id));
            return new ResponseEntity<>(listUser, HttpStatus.OK);
        }
        List<User> listUser = userServiceImpl.findAll();
        return new ResponseEntity<>(listUser, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> addUser(@RequestBody User user) {
        userServiceImpl.save(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deleteUser(@RequestParam(value = "id", required = false) Integer id) {
        userServiceImpl.delete(id);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PatchMapping()
    public ResponseEntity<HttpStatus> editUser(@RequestBody User user, @RequestParam(value = "id", required = false) Integer id) {

        userServiceImpl.update(id, user);

        return ResponseEntity.ok(HttpStatus.OK);
    }
}
