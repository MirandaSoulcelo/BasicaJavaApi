package com.Soucelo.controller;


import com.Soucelo.service.UserService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<String> createUser(@RequestBody UserRequest request)
    {
        User user = new User(request.name(),
                             request.email(),
                             request.password(),
                             request.cpf(),
                             request.excluded(),
                             request.isAdmin());
        service.createUser(user);
        return ResponseEntity.status(201).body("user created!");
    }

    @GetMapping
    public List<User> getUsers() {
        return service.GetUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id)
    {
        return ResponseEntity.ok(service.getUserById(id));
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<String> updateUser(@PathVariable Long id,
                                             @RequestBody UserRequest request)
    {
        User user =new User(id,
                            request.name(),
                            request.email(),
                            request.password(),
                            request.cpf(),
                            request.excluded(),
                            request.isAdmin());

        service.updateUser(user);
        return ResponseEntity.ok("user updated!");
    }

    @PutMapping("/{id}/inactivate")
    public ResponseEntity<String> inactivateUser(@PathVariable Long id)
    {
        service.inactivateUser(id);
        return ResponseEntity.ok("user inactivate!");
    }

    record UserRequest(String name,
                       String email,
                       String password,
                       String cpf,
                       boolean excluded,
                       boolean isAdmin)
    {

    }
}
