package com.Soucelo.service;

import com.Soucelo.domain.model.User;
import com.Soucelo.repository.IUserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService
{
    private final IUserRepository repository;

    public UserService(IUserRepository repository)
    {
        this.repository = repository;
    }

    public void createUser(User user)
    {
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        repository.createUser(user);
    }

    public List<User> GetUsers()
    {
        return repository.getUsers();
    }

    public void updateUser(User user)
    {
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashedPassword);
        repository.updateUser(user);
    }

    public User getUserById(Long id)
    {
        return repository.getUserById(id);
    }

    public void inactivateUser(Long id)
    {
       repository.inactivateUser(id);
    }
}
