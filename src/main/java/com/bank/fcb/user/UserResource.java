package com.bank.fcb.user;

import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
public class UserResource {

    private UserDaoService userService;

    public UserResource(UserDaoService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> get() {
        return userService.getUsers();
    }


    @GetMapping("/users/{name}")
    public User getUserById(@PathVariable String name) {
        User user = userService.getUsersByName(name);
        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        return userService.getUsersByName(name);
    }

    @PostMapping("/users")
    public ResponseEntity<User> create(@Valid @RequestBody User user) {
        userService.createUser(user);
        return ResponseEntity.created(null).build();
    }

}
