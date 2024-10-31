package com.client.rasplus.api.customer.service;


import com.client.rasplus.api.customer.dto.LoginDto;
import com.client.rasplus.api.customer.dto.UserDetailsDto;
import com.client.rasplus.api.customer.dto.UserRepresentationDto;

public interface AuthenticationService {

    String auth(LoginDto dto);

    void sendRecoveryCode(String email);

    boolean recoveryCodeIsValid(String recoveryCode, String email);

    void updatePasswordByRecoveryCode(UserDetailsDto userDetails);

    void createAuthUser(UserRepresentationDto userRepresentation);

    void updateAuthUser(UserRepresentationDto userRepresentation, String email);

}
