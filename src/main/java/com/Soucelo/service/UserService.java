package com.Soucelo.service;

import com.Soucelo.domain.model.User;
import com.Soucelo.dto.request.UserRequestDTO;
import com.Soucelo.dto.response.UserResponseDTO;
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

    public void createUser(UserRequestDTO userRequest)
    {
        if (repository.existsByEmail(userRequest.getEmail()))
            throw new RuntimeException("Email already exists");

        if (repository.existsByCpf(userRequest.getCpf()))
            throw new RuntimeException("CPF already exists");

        String hashedPassword = BCrypt.hashpw(userRequest.getPassword(), BCrypt.gensalt());

        User user = new User(userRequest.getName(),
                             userRequest.getEmail(),
                             hashedPassword,
                             userRequest.getCpf(),
                             userRequest.isExcluded(),
                             userRequest.isAdmin());

        repository.createUser(user);
    }

    public List<UserResponseDTO> getUsers()
    {
        List<User> users = repository.getUsers();

        return users.stream()
                    .map(u -> new UserResponseDTO(u.getId(),
                                                        u.getName(),
                                                        u.getEmail(),
                                                        u.getCpf(),
                                                        u.isAdmin()))
                    .toList();
    }

    public void updateUser(Long id, UserRequestDTO userRequest)
    {
        String hashedPassword = BCrypt.hashpw(userRequest.getPassword(),
                                              BCrypt.gensalt());

        User user = new User(id,
                             userRequest.getName(),
                             userRequest.getEmail(),
                             hashedPassword,
                             userRequest.getCpf(),
                             userRequest.isExcluded(),
                             userRequest.isAdmin());

        repository.updateUser(user);
    }

    public UserResponseDTO getUserById(Long id)
    {
        User user = repository.getUserById(id);

        if(user == null && repository.isInactiveUser(id))
            throw new RuntimeException("You cannot search for inactive users");

        if(user == null)
            throw new RuntimeException("User not found");

        UserResponseDTO userResponse = new UserResponseDTO(user.getId(),
                                                           user.getName(),
                                                           user.getEmail(),
                                                           user.getCpf(),
                                                           user.isAdmin());
        return userResponse;
    }

    public void inactivateUser(Long id)
    {
       repository.inactivateUser(id);
    }
}
