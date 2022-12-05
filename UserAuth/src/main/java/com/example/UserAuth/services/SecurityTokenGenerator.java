package com.example.UserAuth.services;

import com.example.UserAuth.domain.User;

import java.util.Map;

public interface SecurityTokenGenerator {

    Map<Integer, String> generateToken(User user);
}