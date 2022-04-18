package com.heartcooking.auth.security.services;

import com.heartcooking.auth.exception.EmailTakenException;
import com.heartcooking.auth.exception.UsernameTakenException;
import com.heartcooking.auth.models.ERole;
import com.heartcooking.auth.models.Role;
import com.heartcooking.auth.models.User;
import com.heartcooking.auth.payload.request.CreateUserRequest;
import com.heartcooking.auth.payload.request.LoginRequest;
import com.heartcooking.auth.payload.request.SignupRequest;
import com.heartcooking.auth.payload.response.JwtResponse;
import com.heartcooking.auth.repository.RoleRepository;
import com.heartcooking.auth.repository.UserRepository;
import com.heartcooking.auth.security.jwt.JwtUtils;
import com.heartcooking.auth.models.RefreshToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

	final AuthenticationManager authenticationManager;
	final PasswordEncoder encoder;
	final JwtUtils jwtUtils;
	final RefreshTokenServiceImpl refreshTokenService;
	final RoleRepository roleRepository;
	final UserRepository userRepository;

	public JwtResponse loginUser(LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		String jwt = jwtUtils.generateJwtToken(userDetails);

		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

		return new JwtResponse(jwt, refreshToken.getToken(), userDetails.getId(),
				userDetails.getUsername(), userDetails.getEmail(), roles);
	}


	@Override
	public User registerUser(SignupRequest signUpRequest) {
		return createUser(signUpRequest);
	}

	@Override
	public User currentUser() {
		UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userRepository.getById(userDetails.getId());
	}

	private User createUser(SignupRequest signupRequest) {
		return createUser(CreateUserRequest.builder()
				.username(signupRequest.getUsername())
				.email(signupRequest.getEmail())
				.password(signupRequest.getPassword())
				.role(Collections.emptySet())
				.build());
	}

	@Override
	public User createUser(CreateUserRequest createUserRequest) {

		if (userRepository.existsByUsername(createUserRequest.getUsername())) {
			throw new UsernameTakenException(createUserRequest.getUsername());
		}

		if (userRepository.existsByEmail(createUserRequest.getEmail())) {
			throw new EmailTakenException(createUserRequest.getEmail());
		}

		User user = new User(createUserRequest.getUsername(),
				createUserRequest.getEmail(),
				encoder.encode(createUserRequest.getPassword()));

		Set<Role> roles = getRoles(createUserRequest);
		user.setRoles(roles);
		userRepository.save(user);

		return user;
	}

	private Set<Role> getRoles(CreateUserRequest signUpRequest) {

		Set<String> stringRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (stringRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			stringRoles.forEach(role -> {
				switch (role) {
					case "admin":
						Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(adminRole);

						break;
					case "mod":
						Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(modRole);

						break;
					default:
						Role userRole = roleRepository.findByName(ERole.ROLE_USER)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(userRole);
				}
			});
		}
		return roles;
	}
}
