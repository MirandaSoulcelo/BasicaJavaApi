package com.Soucelo.controller;


import com.Soucelo.dto.request.UserRequestDTO;
import com.Soucelo.dto.response.UserResponseDTO;
import com.Soucelo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> createUser(@RequestBody UserRequestDTO request)
    {
        service.createUser(request);
        return ResponseEntity.status(201).body("user created!");
    }

    @GetMapping
    public List<UserResponseDTO> getUsers()
    {
        return service.getUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id)
    {
        return ResponseEntity.ok(service.getUserById(id));
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<String> updateUser(@PathVariable Long id,
                                             @RequestBody UserRequestDTO request)
    {


        service.updateUser(id, request);
        return ResponseEntity.ok("user updated!");
    }

    @PutMapping("/{id}/inactivate")
    public ResponseEntity<String> inactivateUser(@PathVariable Long id)
    {
        service.inactivateUser(id);
        return ResponseEntity.ok("user inactivate!");
    }
}
