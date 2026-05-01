package com.Soucelo.repository;

import com.Soucelo.domain.model.User;

import java.util.List;

public interface IUserRepository
{
    List<User> getUsers();
    void createUser(User user);
    void updateUser(User user);
    User getUserById(Long id);
    void inactivateUser(Long id);
}
