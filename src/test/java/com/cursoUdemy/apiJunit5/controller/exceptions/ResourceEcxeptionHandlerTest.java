package com.cursoUdemy.apiJunit5.controller.exceptions;

import com.cursoUdemy.apiJunit5.service.exceptions.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResourceEcxeptionHandlerTest {

    @InjectMocks
    private ResourceEcxeptionHandler ecxeptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void whenObjectNotFoundExceptionThenReturnAResposseEntity() {
        ResponseEntity<StandarError> response = ecxeptionHandler.objectNotFound(new ObjectNotFoundException("Usuario não encontrado"), new MockHttpServletRequest());
        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(StandarError.class, response.getBody().getClass());
        assertEquals("Usuario não encontrado", response.getBody().getError());
        assertEquals(404, response.getBody().getStatus());
    }

    @Test
    void dataIntegratyViolationException() {
    }
}