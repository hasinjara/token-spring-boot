package com.demo.token.config;


import com.demo.token.models.Role;
import com.demo.token.models.User;
import com.demo.token.repositories.UserRepository;
import com.demo.token.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class SeedDataConfig implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;



    @Override
    public void run(String... args) throws Exception {
        if (userRepository.count() == 0) {

            User admin = User
                    .builder()
                    .nom("admin")
                    .prenom("admin")
                    .mail("admin@admin.com")
                    //.mdp(passwordEncoder.encode("admin"))
                    .mdp("admin")
                    .role(Role.ROLE_ADMIN)
                    .build();

            userService.save(admin);
            log.debug("created ADMIN user - {}", admin);
        }
    }
}
