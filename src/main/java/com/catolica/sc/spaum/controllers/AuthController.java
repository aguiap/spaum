package com.catolica.sc.spaum.controllers;

import com.catolica.sc.spaum.data.vo.v1.security.AccountCredentialVO;
import com.catolica.sc.spaum.exceptions.ExceptionHelper;
import com.catolica.sc.spaum.services.AuthServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentication Endpoint")
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthServices authServices;

    @SuppressWarnings("rawtypes")
    @Operation(summary = "Authenticates a user and return a token")
    @PostMapping(value = "/signin")
    public ResponseEntity signin(@RequestBody AccountCredentialVO data) throws Exception {
        if(checkIfParamsIsNotNull(data))
            throw ExceptionHelper.usernameOrPasswordInvalid();

        data.setPassword(data.getPassword());
        var token = authServices.signin(data);
        if(token == null)
            throw ExceptionHelper.usernameOrPasswordInvalid();
        return token;
    }

    @SuppressWarnings("rawtypes")
    @Operation(summary = "Refresh token for authenticated user and returns a token")
    @PutMapping(value = "/refresh/{username}")
    public ResponseEntity refreshToken(@PathVariable String username, @RequestHeader("Authorization") String refreshToken){
        if(checkIfParamsIsNotNull(username, refreshToken))
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");

        var token = authServices.refreshToken(username, refreshToken);
        if(token == null)
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");
        return token;
    }

    private boolean checkIfParamsIsNotNull(AccountCredentialVO data){
        return data == null || data.getUsername() == null || data.getUsername().isBlank() ||
                data.getPassword() == null || data.getPassword().isBlank();
    }

    private boolean checkIfParamsIsNotNull(String username, String refreshToken){
        return refreshToken == null || refreshToken.isBlank() || username == null || username.isBlank();
    }
}
