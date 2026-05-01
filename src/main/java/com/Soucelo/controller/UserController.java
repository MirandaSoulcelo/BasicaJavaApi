package com.Soucelo.controller;


import com.Soucelo.service.UserService;
import org.springframework.web.bind.annotation.*;
import com.Soucelo.domain.model.User;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController
{
    private final UserService service;

    public UserController(UserService service)
    {
        this.service = service;
    }

    @PostMapping
    public String createUser(@RequestBody UserRequest request)
    {
        service.CreateUser(request.name(),
                           request.email());
        return "user created!";
    }

    @GetMapping
    public List<User> getUsers() {
        return service.GetUsers();
    }

    record UserRequest(String name,
                       String email)
    {

    }
}
