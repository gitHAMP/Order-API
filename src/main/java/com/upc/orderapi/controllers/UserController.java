package com.upc.orderapi.controllers;

import com.upc.orderapi.converters.UserConverter;
import com.upc.orderapi.dtos.LoginRequestDTO;
import com.upc.orderapi.dtos.LoginResponseDTO;
import com.upc.orderapi.dtos.SignupRequestDTO;
import com.upc.orderapi.dtos.UserDTO;
import com.upc.orderapi.entity.User;
import com.upc.orderapi.services.UserService;
import com.upc.orderapi.utils.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserConverter userConverter;


    @PostMapping("/signup")
    public ResponseEntity<WrapperResponse<UserDTO>> signup(@RequestBody SignupRequestDTO request){
        User user=userService.createUser(userConverter.signup(request));
        return new WrapperResponse<>(true,"success",userConverter.fromEntity(user))
                .createResponse();
    }

    @PostMapping("/login")
    public ResponseEntity<WrapperResponse<LoginResponseDTO>> login(@RequestBody LoginRequestDTO request){
       LoginResponseDTO response=userService.login(request);
        return new WrapperResponse<>(true,"success",response)
                .createResponse();
    }

}
