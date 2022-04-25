package com.cursoUdemy.apiJunit5.service;

import com.cursoUdemy.apiJunit5.model.User;
import com.cursoUdemy.apiJunit5.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    public static final long ID = 1L;
    public static final String NAME = "Cassio";
    public static final String EMAIL = "cassio@teste.com";
    public static final String PASSWORD = "cassio123";
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private User user;

    private Optional<User> userOptional;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void save() {
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        Mockito.when(userRepository.findById(Mockito.anyLong())).thenReturn(userOptional);
        User response = userService.findById(ID);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(User.class, response.getClass());
        Assertions.assertEquals(ID, response.getId());
        Assertions.assertEquals(NAME, response.getName());
        Assertions.assertEquals(EMAIL, response.getEmail());
        Assertions.assertEquals(PASSWORD, response.getPassword());

    }

    @Test
    void findAll() {
    }

    @Test
    void findByName() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    private void startUser(){
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userOptional = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
    }
}