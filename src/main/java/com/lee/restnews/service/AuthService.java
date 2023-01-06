package com.lee.restnews.service;

import com.lee.restnews.dto.LoginDto;
import com.lee.restnews.dto.RegisterDto;

public interface AuthService {

    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
