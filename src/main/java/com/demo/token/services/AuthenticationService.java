package com.demo.token.services;

import com.demo.token.dto.JwtAuthenticationResponse;
import com.demo.token.dto.SignInRequest;
import com.demo.token.dto.SignUpRequest;
import com.demo.token.dto.UserDto;
import com.demo.token.models.Retour;
import com.demo.token.models.Role;
import com.demo.token.models.User;
import com.demo.token.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


//    public Retour signup(SignUpRequest request) {
//         JwtAuthenticationResponse jwt = _signup(request);
//
//    }

    public Retour signup(SignUpRequest request) {
        var user = User
                .builder()
                .nom(request.getNom())
                .prenom(request.getPrenom())
                .mail(request.getMail())
                .mdp(passwordEncoder.encode(request.getMdp()))
                //.mdp(request.getMdp())
                .role(Role.ROLE_USER)
                .build();


        user = userService.save(user);
        var jwt = jwtService.generateToken(user);
        JwtAuthenticationResponse token = JwtAuthenticationResponse.builder().token(jwt).build();
        UserDto user_dto =  UserDto.builder().idUser(user.getIdUsers()).build();
        Object[] response = new Object[2];
        response[0] = user_dto;
        response[1] = token;
        Retour r = new Retour(response);
        return r;
    }


    public Retour signin(SignInRequest request) {
        try {
                authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getMail(), request.getMdp()));
        }
        catch (Exception e) {
            return error_sigin();
        }

        User user = userRepository.findByMail(request.getMail())
                .orElse( null_user() );
        //System.out.println("--===============================");
        //System.out.println(user);
        if(user == null) {
            //System.out.println("--===============================");
            return error_sigin();
        }
        UserDto user_dto = UserDto.builder().idUser(user.getIdUsers()).build();
        var jwt = jwtService.generateToken(user);
        JwtAuthenticationResponse token = JwtAuthenticationResponse.builder().token(jwt).build();
        Object[] response = new Object[2];
        response[0] = user_dto;
        response[1] = token;
        Retour r = new Retour(response);
        return r;
    }

    public Retour error_sigin(){
        return new Retour("Error Sign in","Authentication Failed",null);
    }

    public User null_user() {
        return null;
    }

//    public JwtAuthenticationResponse signup(SignUpRequest request) {
//        var user = User
//                .builder()
//                .nom(request.getNom())
//                .prenom(request.getPrenom())
//                .mail(request.getMail())
//                .mdp(passwordEncoder.encode(request.getMdp()))
//                //.mdp(request.getMdp())
//                .role(Role.ROLE_USER)
//                .build();
//
//
//        user = userService.save(user);
//        var jwt = jwtService.generateToken(user);
//        JwtAuthenticationResponse token = JwtAuthenticationResponse.builder().token(jwt).build();
//        UserDto user_dto =  UserDto.builder().idUser(user.getIdUsers()).build();
//        Object[] response = new Object[2];
//        response[0] = user_dto;
//        response[1] = token;
//        Retour r = new Retour(response);
//        return JwtAuthenticationResponse.builder().token(jwt).build();
//    }
//
//
//    public JwtAuthenticationResponse signin(SignInRequest request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.getMail(), request.getMdp()));
//        var user = userRepository.findByMail(request.getMail())
//                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
//        var jwt = jwtService.generateToken(user);
//        return JwtAuthenticationResponse.builder().token(jwt).build();
//    }


}
