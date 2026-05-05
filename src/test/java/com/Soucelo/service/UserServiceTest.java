package com.Soucelo.service;

import com.Soucelo.domain.model.User;
import com.Soucelo.dto.request.UserRequestDTO;
import com.Soucelo.dto.response.UserResponseDTO;
import com.Soucelo.repository.IUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest
{
    @Mock
    IUserRepository repository;

    @InjectMocks
    UserService service;

    UserRequestDTO userValidRequest()
    {
        return new UserRequestDTO("João",
                                  "joao@gmail.com",
                                  "123.567.789-00",
                                  "senha123",
                                  false,
                                  false);
    }

    @Test
    void createUser_shouldCreateUser_whenHasValidData()
    {
        when(repository.existsByEmail("joao@gmail.com")).thenReturn(false);
        when(repository.existsByCpf("123.567.789-00")).thenReturn(false);

        service.createUser(userValidRequest());

        verify(repository, times(1)).createUser(any(User.class));
    }

    @Test
    void createUser_shouldThrowException_whenEmailItAlreadyExists()
    {
        when(repository.existsByEmail("joao@gmail.com")).thenReturn(true);

        assertThatThrownBy(() -> service.createUser(userValidRequest()))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Email already exists");

        verify(repository, never()).createUser(any());

    }

    @Test
    void createUser_shouldThrowException_whenCpfAlreadyExists()
    {
        when(repository.existsByEmail("joao@gmail.com")).thenReturn(false);
        when(repository.existsByCpf("123.567.789-00")).thenReturn(true);

        assertThatThrownBy(()-> service.createUser(userValidRequest()))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("CPF already exists");

        verify(repository, never()).createUser(any());
    }

    @Test
    void getUserById_shouldReturnAnActiveUser_whenUserAlreadyExists()
    {
        when(repository.getUserById(1L)).thenReturn( new User(1L,
                                                              "João",
                                                              "Joao@gmail.com",
                                                              "senha123",
                                                              "123.567.789-00",
                                                              false,
                                                              false));

        UserResponseDTO response = service.getUserById(1L);

        assertThat(response.getName()).isEqualTo("João");
        assertThat(response.getEmail()).isEqualTo("Joao@gmail.com");

        verify(repository, times(1)).getUserById(1L);
    }

    @Test
    void getUserById_shouldNotReturnAnUser_whenUserIsNull()
    {
        when(repository.getUserById(1L)).thenReturn(null);
        when(repository.isInactiveUser(1L)).thenReturn(false);

        assertThatThrownBy(()-> service.getUserById(1L))
                .isInstanceOf(RuntimeException.class)
                        .hasMessage("User not found");
    }

    @Test
    void getUserById_shouldNotReturnAnUser_whenUserIsInactive()
    {
        when(repository.getUserById(1L)).thenReturn(null);

        when(repository.isInactiveUser(1L)).thenReturn(true);

        assertThatThrownBy(()-> service.getUserById(1L))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("You cannot search for inactive users");
    }
}
