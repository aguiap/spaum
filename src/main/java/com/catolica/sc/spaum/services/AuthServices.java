package com.catolica.sc.spaum.services;


import com.catolica.sc.spaum.data.vo.v1.security.AccountCredentialVO;
import com.catolica.sc.spaum.data.vo.v1.security.TokenVO;
import com.catolica.sc.spaum.exceptions.ExceptionHelper;
import com.catolica.sc.spaum.repositories.UserRepository;
import com.catolica.sc.spaum.security.jwt.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServices {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    public ResponseEntity<TokenVO> signin(AccountCredentialVO data){
        try{
            var username = data.getUsername();
            var password = data.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            var user = repository.findByUserName(username);

            var tokenResponse = new TokenVO();

            if (user == null) {
                throw ExceptionHelper.usernameOrPasswordInvalid();
            } else {
                tokenResponse = tokenProvider.createAccessToken(username, user.getRoles());
            }
            return ResponseEntity.ok(tokenResponse);
        }catch (Exception e){
            throw ExceptionHelper.usernameOrPasswordInvalid();
        }

    }

    public ResponseEntity<TokenVO> refreshToken(String username, String refreshToken){
        var user = repository.findByUserName(username);

        var tokenResponse = new TokenVO();

        if (user == null) {
            throw new UsernameNotFoundException("Username " + username + "not found!");
        } else {
            tokenResponse = tokenProvider.refreshToken(refreshToken);
        }
        return ResponseEntity.ok(tokenResponse);
    }
}
