package com.rizamo.goldStore.features.auth;

import com.rizamo.goldStore.features.auth.DTO.TokenDTO;
import com.rizamo.goldStore.features.auth.jwt.JwtHelper;
import com.rizamo.goldStore.features.auth.authException.LoginFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtHelper jwtHelper;


    @Autowired
    public AuthService(AuthenticationManager authenticationManager1, JwtHelper jwtHelper) {
        this.authenticationManager = authenticationManager1;
        this.jwtHelper = jwtHelper;
    }

    public TokenDTO authUser(String username, String password) {
        Authentication authenticationRequest = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = authenticationManager.authenticate(authenticationRequest);

        if (authentication.isAuthenticated()){
            return new TokenDTO(jwtHelper.generateToken(username));
        }
        throw new LoginFailedException("user not found");
    }

}
