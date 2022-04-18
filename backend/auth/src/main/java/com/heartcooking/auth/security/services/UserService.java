package com.heartcooking.auth.security.services;

import com.heartcooking.auth.models.User;
import com.heartcooking.auth.payload.request.CreateUserRequest;
import com.heartcooking.auth.payload.request.LoginRequest;
import com.heartcooking.auth.payload.request.SignupRequest;
import com.heartcooking.auth.payload.response.JwtResponse;

public interface UserService {

	User createUser(CreateUserRequest createUserRequest);

	JwtResponse loginUser(LoginRequest loginRequest);

	User registerUser(SignupRequest signUpRequest);

	User currentUser();
}
