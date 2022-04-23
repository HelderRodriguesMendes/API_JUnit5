package com.cursoUdemy.apiJunit5.controller;

import com.cursoUdemy.apiJunit5.model.User;
import com.cursoUdemy.apiJunit5.model.dto.UserDTO;
import com.cursoUdemy.apiJunit5.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id){
        User user = userService.findById(id);
        return new ResponseEntity<>(mapper.map(user, UserDTO.class), HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<UserDTO>> findAll(){
        List<UserDTO> userDTOS = userService.findAll().stream().map(user -> mapper.map(user, UserDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }
}
