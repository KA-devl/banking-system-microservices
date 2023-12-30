package com.banking.bankapi.services.impl;

import com.banking.bankapi.config.JwtUtils;
import com.banking.bankapi.dto.AccountDto;
import com.banking.bankapi.dto.AuthenticationRequest;
import com.banking.bankapi.dto.AuthenticationResponse;
import com.banking.bankapi.dto.UserDto;
import com.banking.bankapi.models.Role;
import com.banking.bankapi.models.User;
import com.banking.bankapi.repositories.UserRepository;
import com.banking.bankapi.services.AccountService;
import com.banking.bankapi.services.UserService;
import com.banking.bankapi.validators.ObjectsValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String ROLE_USER = "ROLE_USER";
    private final UserRepository userRepository;
    private final ObjectsValidator<UserDto> validator;
    private final AccountService accountService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;


    @Override
    public Integer save(UserDto dto) {
        validator.validate(dto); //validate the object before saving it
        User user = UserDto.toEntity(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user).getId();
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()//Transform all users to userDto
                .map(user -> UserDto.fromEntity(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Integer id) {
        return userRepository.findById(id)
                .map(user -> UserDto.fromEntity(user))
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    @Override
    public void delete(Integer id) {
        //check before delete
        userRepository.deleteById(id);
    }

    @Override
    public Integer validateAccount(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        //activate user
        user.setActive(true);
        //create a bank account for the user
        AccountDto accountDto = AccountDto.builder()
                .user(UserDto.fromEntity(user))
                .build();
        accountService.save(accountDto);
        userRepository.save(user);
        return user.getId();
    }

    @Override
    public Integer invalidateAccount(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
        //deactivate user
        user.setActive(false);
        userRepository.save(user);
        return user.getId();
    }

    @Override
    @Transactional
    public AuthenticationResponse register(UserDto dto) {
        validator.validate(dto); //validate the object before saving it
        User user = UserDto.toEntity(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(findOrCreateRole(ROLE_USER));
        User savedUser = userRepository.save(user);
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", savedUser.getId());
        claims.put("fullName", savedUser.getFirstname() + " " + savedUser.getLastname());
        String token = jwtUtils.generateToken(savedUser, claims);

        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        //if authentication is successful, generate a token, else exception is thrown by handler
        final User user = userRepository.findByEmail(request.getEmail()).get();
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getUsername());
        final String token = jwtUtils.generateToken(user, claims);

        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    private Role findOrCreateRole(String roleName) {
        Role role = roleRepository.findByName(roleName)
                .orElse(null);
        if(role == null) {
            role = roleRepository.save(Role.builder().name(roleName).build());
        }
        return role;
    }
}
