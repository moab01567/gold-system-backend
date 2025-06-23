package com.rizamo.goldStore.features.auth;

import com.rizamo.goldStore.features.auth.DTO.TokenDTO;
import com.rizamo.goldStore.features.auth.DTO.UserLoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/auth/login")
    public ResponseEntity<TokenDTO> authLogin(@RequestBody UserLoginDTO userLogin) {
        TokenDTO tokenDTO = authService.authUser(userLogin.getUsername(), userLogin.getPassword());
        return new ResponseEntity<>(tokenDTO, HttpStatus.OK);
    }

    @GetMapping("/auth/validate")
    public ResponseEntity<HttpStatus> authValidate() {
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
