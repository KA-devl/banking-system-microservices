package com.banking.bankapi.services;

import com.banking.bankapi.dto.AuthenticationRequest;
import com.banking.bankapi.dto.AuthenticationResponse;
import com.banking.bankapi.dto.UserDto;

public interface UserService extends AbstractService<UserDto> {

    Integer validateAccount(Integer id);

    Integer invalidateAccount(Integer id);


    AuthenticationResponse register(UserDto userDto);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
