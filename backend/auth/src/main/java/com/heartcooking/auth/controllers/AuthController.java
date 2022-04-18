package com.heartcooking.auth.controllers;

import com.heartcooking.auth.exception.EmailTakenException;
import com.heartcooking.auth.exception.ErrorMessage;
import com.heartcooking.auth.exception.UsernameTakenException;
import com.heartcooking.auth.models.User;
import com.heartcooking.auth.payload.request.LoginRequest;
import com.heartcooking.auth.payload.request.CreateUserRequest;
import com.heartcooking.auth.payload.request.SignupRequest;
import com.heartcooking.auth.payload.request.TokenRefreshRequest;
import com.heartcooking.auth.payload.response.JwtResponse;
import com.heartcooking.auth.payload.response.TokenRefreshResponse;
import com.heartcooking.auth.repository.RoleRepository;
import com.heartcooking.auth.repository.UserRepository;
import com.heartcooking.auth.security.jwt.JwtUtils;
import com.heartcooking.auth.security.services.RefreshTokenService;
import com.heartcooking.auth.security.services.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.Date;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@RestController
@Slf4j
public class AuthController {

	final AuthenticationManager authenticationManager;
	final JwtUtils jwtUtils;
	final PasswordEncoder encoder;
	final RefreshTokenService refreshTokenService;
	final RoleRepository roleRepository;
	final UserRepository userRepository;
	final UserService userService;

	@PostMapping("/login")
	public ResponseEntity<JwtResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		return ResponseEntity.ok(userService.loginUser(loginRequest));
	}

	@ApiOperation(value = "Register user", notes = "Endpoint for creating new users")
	@PostMapping("/signup")
	public ResponseEntity<User> registerUser(@ApiParam(value = "signup request") @Valid @RequestBody SignupRequest signUpRequest) {

		return ResponseEntity.ok(userService.registerUser(signUpRequest));
	}

	@PostMapping("/create-user")
	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<User> createUser(@ApiParam(value = "signup request") @Valid @RequestBody CreateUserRequest createUserRequest) {

		return ResponseEntity.ok(userService.createUser(createUserRequest));
	}


	@PostMapping("/refreshtoken")
	public ResponseEntity<TokenRefreshResponse> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {

		return ResponseEntity.ok(refreshTokenService.refreshToken(request));
	}

	@ExceptionHandler({EmailTakenException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessage emailTakenError(EmailTakenException exc, WebRequest request) {

		log.info("There was attempt to register at taken email address: {}" + exc.getEmail());
		return new ErrorMessage(
				HttpStatus.BAD_REQUEST.value(),
				new Date(),
				exc.getMessage(),
				request.getDescription(false));
	}

	@ExceptionHandler({UsernameTakenException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessage usernameTakenError(UsernameTakenException exc, WebRequest request) {

		log.info("There was attempt to register at taken username: {}" + exc.getUsername());
		return new ErrorMessage(
				HttpStatus.BAD_REQUEST.value(),
				new Date(),
				exc.getMessage(),
				request.getDescription(false));
	}
}
