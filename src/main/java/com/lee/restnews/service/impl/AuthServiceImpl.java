package com.lee.restnews.service.impl;

import com.lee.restnews.dto.LoginDto;
import com.lee.restnews.dto.RegisterDto;
import com.lee.restnews.entity.Role;
import com.lee.restnews.entity.User;
import com.lee.restnews.exception.ApiException;
import com.lee.restnews.repository.RoleRepository;
import com.lee.restnews.repository.UserRepository;
import com.lee.restnews.security.JwtTokenProvider;
import com.lee.restnews.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder,
                           JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsernameOrEmail(), loginDto.getPassword()
                ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }

    @Override
    public String register(RegisterDto registerDto) {

        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Username is already exists!");
        }

        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Email is already exists!");
        }

        User user = new User();
        user.setFullName(registerDto.getFullName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        user.setRoles(roles);

        userRepository.save(user);

        return "User registered successfully!";
    }
}