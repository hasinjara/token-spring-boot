package com.demo.token.services;

import com.demo.token.models.User;
import com.demo.token.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                return userRepository.findByMail(username).orElseThrow( () -> new UsernameNotFoundException("User not found") );
            }
        };
    }

    public User save(User newUser) {
        return userRepository.save(newUser);
    }

}
