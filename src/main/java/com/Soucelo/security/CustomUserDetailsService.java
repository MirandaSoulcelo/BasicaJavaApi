package com.Soucelo.security;

import com.Soucelo.domain.model.User;
import com.Soucelo.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private IUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        User user = repository.getUserByEmail(email);

        if(user == null)
            throw new UsernameNotFoundException("Usuário não encontrado");

        return new CustomUserDetails(user);
    }
}
