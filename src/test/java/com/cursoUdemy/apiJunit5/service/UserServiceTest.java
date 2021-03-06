package com.cursoUdemy.apiJunit5.service;

import com.cursoUdemy.apiJunit5.model.User;
import com.cursoUdemy.apiJunit5.model.dto.UserDTO;
import com.cursoUdemy.apiJunit5.repository.UserRepository;
import com.cursoUdemy.apiJunit5.service.exceptions.DataIntegratyViolationException;
import com.cursoUdemy.apiJunit5.service.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {

    public static final long ID = 1L;
    public static final String NAME = "Cassio";
    public static final String EMAIL = "cassio@teste.com";
    public static final String PASSWORD = "cassio123";
    public static final int INDEX = 0;
    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    private User user;

    private UserDTO userDTO;

    private Optional<User> userOptional;

    @Mock
    private ModelMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenCreateThenReturnSUCCESS() {
        when(userRepository.save(any())).thenReturn(user);
        User response = userService.save(user);
        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void whenCreateThenReturnAnDataIntegrityViolationException() {
        when(userRepository.findByEmail(anyString())).thenReturn(userOptional);

        try{
            user.setId(2L);
            userService.save(user);
        }catch (Exception e){
            assertEquals(DataIntegratyViolationException.class, e.getClass());
            assertEquals("O email informado j?? esta cadastrado", e.getMessage());
        }
    }

    @Test
    void whenFindByIdThenReturnAnUserInstance() {
        when(userRepository.findById(anyLong())).thenReturn(userOptional);
        User response = userService.findById(ID);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void whenFindByIdThenReturnAnObjectNotFoundException(){
        when(userRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException("Usuario n??o encontrado"));
        try {
            userService.findById(ID);
        }catch (Exception e){
            assertEquals(ObjectNotFoundException.class, e.getClass());
            assertEquals("Usuario n??o encontrado", e.getMessage());
        }
    }

    @Test
    void whenFindAllThenReturnAnListOfUsers() {
        when(userRepository.findAll()).thenReturn(List.of(user));
        List<User> users = userService.findAll();

        assertNotNull(users);
        assertEquals(1, users.size());
        assertEquals(User.class, users.get(INDEX).getClass());

        assertEquals(ID, users.get(INDEX).getId());
        assertEquals(NAME, users.get(INDEX).getName());
        assertEquals(EMAIL, users.get(INDEX).getEmail());
        assertEquals(PASSWORD, users.get(INDEX).getPassword());

    }

    @Test
    void whenFindByNameThenReturnAnUserInstance() {
        when(userRepository.findByName("Cassio")).thenReturn(List.of(user));
        List<User> users = userService.findByName("Cassio");
        assertNotNull(users);
        assertEquals(1, users.size());
        assertEquals(User.class, users.get(INDEX).getClass());

        assertEquals(ID, users.get(INDEX).getId());
        assertEquals(NAME, users.get(INDEX).getName());
        assertEquals(EMAIL, users.get(INDEX).getEmail());
        assertEquals(PASSWORD, users.get(INDEX).getPassword());

    }

    @Test
    void whenUpdateThenReturnSUCCESS() {
        when(userRepository.save(any())).thenReturn(user);
        when(mapper.map(any(), any())).thenReturn(user);
        User response = userService.update(userDTO);
        assertNotNull(response);
        assertEquals(User.class, response.getClass());
        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void whenUpdateThenReturnAnDataIntegrityViolationException() {
        when(userRepository.findByEmail(anyString())).thenReturn(userOptional);
        when(mapper.map(any(), any())).thenReturn(user);

        try{
            user.setId(2L);
            userService.update(userDTO);
        }catch (Exception e){
           assertEquals(DataIntegratyViolationException.class, e.getClass());
            //assertEquals("O email informado j?? esta cadastrado", e.getMessage());
        }
    }

    @Test
    void deleteWithSuccess() {
        when(userRepository.findById(anyLong())).thenReturn(userOptional);
        doNothing().when(userRepository).deleteById(anyLong());
        userService.delete(ID);
        verify(userRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void deleteWithObjectNotFoundEsception(){
        when(userRepository.findById(anyLong())).thenThrow(new ObjectNotFoundException("Usuario n??o encontrado"));
        try{
            userService.delete(ID);
        }catch (Exception e){
            assertEquals(ObjectNotFoundException.class, e.getClass());
            assertEquals("Usuario n??o encontrado", e.getMessage());
        }
    }

    private void startUser(){
        user = new User(ID, NAME, EMAIL, PASSWORD);
        userOptional = Optional.of(new User(ID, NAME, EMAIL, PASSWORD));
        userDTO = new UserDTO(ID, NAME, EMAIL, PASSWORD);
    }
}