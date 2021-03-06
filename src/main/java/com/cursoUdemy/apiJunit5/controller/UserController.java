package com.cursoUdemy.apiJunit5.controller;

import com.cursoUdemy.apiJunit5.model.User;
import com.cursoUdemy.apiJunit5.model.dto.UserDTO;
import com.cursoUdemy.apiJunit5.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper mapper;

    @PostMapping("/save")
    public ResponseEntity<UserDTO> save(@RequestBody UserDTO userDTO){
        User user = userService.save(mapper.map(userDTO, User.class));
        return  new ResponseEntity<>(mapper.map(user, UserDTO.class), HttpStatus.CREATED);
    }

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

    @GetMapping("/findByName")
    public ResponseEntity<List<UserDTO>> findByName(@RequestParam String name){
        List<User> users = userService.findByName(name).stream().sorted(Comparator.comparing(User::getName)).collect(Collectors.toList()); //ordenando a lista por name no java 8
        List<UserDTO> userDTOS = users.stream().map(user -> mapper.map(user, UserDTO.class)).collect(Collectors.toList());

        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    @PutMapping("/upadate/{id}")
    public ResponseEntity<UserDTO> upadate(@PathVariable Long id, @RequestBody UserDTO userDTO){
        userDTO.setId(id);
        return  new ResponseEntity<>(mapper.map(userService.update(userDTO), UserDTO.class), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserDTO> delete(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
