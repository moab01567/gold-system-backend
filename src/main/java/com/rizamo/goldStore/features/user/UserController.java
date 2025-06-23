package com.rizamo.goldStore.features.user;

import com.rizamo.goldStore.MessageDTO;
import com.rizamo.goldStore.features.user.DTO.GetUserDTO;
import com.rizamo.goldStore.features.user.DTO.PostChangePwdDTO;
import com.rizamo.goldStore.features.user.DTO.PostNewUserDTO;
import com.rizamo.goldStore.features.user.DTO.PutUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {


    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<GetUserDTO> getUser(Principal principal){
        return new ResponseEntity<>(userService.getUser(principal.getName()), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<GetUserDTO>> getAllUser(){
        return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<MessageDTO> createUser(@RequestBody PostNewUserDTO postNewUserDTO, Principal principal){
        userService.createUser(postNewUserDTO, principal.getName());
        return new ResponseEntity<>(new MessageDTO(201,"User was created"), HttpStatus.CREATED);
    }
    @PutMapping("/change/password")
    public ResponseEntity<MessageDTO> changePassword(@RequestBody PostChangePwdDTO postChangePwdDTO, Principal principal){
        userService.changePassword(postChangePwdDTO, principal.getName());
        return new ResponseEntity<>(
                new MessageDTO(204, "Resource updated successfully"),
                HttpStatus.OK
        );
    }
    @PutMapping("/update")
    public ResponseEntity<MessageDTO> updateUserInfo(@RequestBody PutUserDTO putUserDTO, Principal principal) {
        userService.updateUser(putUserDTO,principal.getName());
        return new ResponseEntity<>(
                new MessageDTO(204, "Resource updated successfully"),
                HttpStatus.OK);
    }

}
