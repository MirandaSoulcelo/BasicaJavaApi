package com.Soucelo.service;

import com.Soucelo.domain.model.User;
import com.Soucelo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService
{
    private final UserRepository repository;

    public UserService(UserRepository repository)
    {
        this.repository = repository;
    }

    public void CreateUser(String name,
                           String email)
    {
        User user = new User(name, email);
        repository.save(user);
    }

    public List<User> GetUsers()
    {
        return repository.GetUsers();
    }
}
