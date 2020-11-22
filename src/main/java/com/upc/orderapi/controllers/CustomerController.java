package com.upc.orderapi.controllers;

import com.upc.orderapi.converters.UserConverter;
import com.upc.orderapi.dtos.ProductDTO;
import com.upc.orderapi.dtos.UserDTO;
import com.upc.orderapi.entity.Product;
import com.upc.orderapi.entity.User;
import com.upc.orderapi.services.UserService;
import com.upc.orderapi.utils.WrapperResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserConverter userConverter;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){


        List<User> users = userService.findAll();
        List<UserDTO> dtoUsers = userConverter.fromEntity(users);

        return new WrapperResponse(true, "success", dtoUsers)
                .createResponse(HttpStatus.OK);
    }
}
