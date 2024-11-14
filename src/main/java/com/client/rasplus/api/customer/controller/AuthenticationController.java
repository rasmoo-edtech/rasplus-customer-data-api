package com.client.rasplus.api.customer.controller;

import com.client.rasplus.api.customer.dto.LoginDto;
import com.client.rasplus.api.customer.dto.UserDetailsDto;
import com.client.rasplus.api.customer.dto.UserRepresentationDto;
import com.client.rasplus.api.customer.model.UserRecoveryCode;
import com.client.rasplus.api.customer.service.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/v1/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> auth(@RequestBody @Valid LoginDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(authenticationService.auth(dto));
    }

    @PostMapping(value = "/recovery-code/send",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(value = "hasAnyAuthority('client_read_write','admin_read','admin_write')")
    public ResponseEntity<Void> sendRecoveryCode(@RequestBody @Valid UserRecoveryCode dto) {
        authenticationService.sendRecoveryCode(dto.getEmail());
        return ResponseEntity.noContent().build();
    }


    @GetMapping(value = "/recovery-code")
    @PreAuthorize(value = "hasAnyAuthority('client_read_write','admin_read','admin_write')")
    public ResponseEntity<Void> recoveryCodeIsValid(@RequestParam("recoveryCode") String recoveryCode,
                                                 @RequestParam("email") String email) {
        authenticationService.recoveryCodeIsValid(recoveryCode, email);
        return ResponseEntity.noContent().build();
    }


    @PatchMapping(value = "/recovery-code/password", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize(value = "hasAnyAuthority('client_read_write','admin_read','admin_write')")
    public ResponseEntity<Void> updatePasswordByRecoveryCode(@RequestBody @Valid UserDetailsDto dto) {
        authenticationService.updatePasswordByRecoveryCode(dto);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/representations/credentials")
    @PreAuthorize(value = "hasAnyAuthority('admin_read','admin_write')")
    public ResponseEntity<Void> createAuthUser(@Valid @RequestBody UserRepresentationDto dto) {
        authenticationService.createAuthUser(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/representations/credentials/{email}")
    @PreAuthorize(value = "hasAnyAuthority('admin_read','admin_write')")
    public ResponseEntity<Void> updateAuthUser(@Valid @RequestBody UserRepresentationDto dto, @PathVariable("email") String email) {
        authenticationService.updateAuthUser(dto, email);
        return ResponseEntity.noContent().build();
    }
}
