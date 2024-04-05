package ru.kata.spring.boot_security.demo.DataBase;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.services.UserServiceImpl;


@Component
public class DataInitializer implements CommandLineRunner {
    private final UserService userService;

    public DataInitializer(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        Role adminRole = new Role("ROLE_ADMIN");

        User user = new User("User", "user", 20);

        User admin = new User("Admin", "admin", 25);
        admin.addRoleToUser(adminRole);


        userService.save(user);
        userService.save(admin);
    }
}
