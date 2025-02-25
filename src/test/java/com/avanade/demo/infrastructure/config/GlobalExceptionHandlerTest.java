package com.avanade.demo.infrastructure.config;

import com.avanade.demo.domain.exception.EntityAlreadyExistsException;
import com.avanade.demo.domain.exception.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@SpringBootTest
@ActiveProfiles("test")
class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void handleGlobalExceptionReturnsInternalServerError() {
        Exception ex = new Exception("Internal error");
        WebRequest request = mock(WebRequest.class);

        ResponseEntity<?> response = handler.handleGlobalException(ex, request);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Internal error", response.getBody());
    }

    @Test
    void handleResourceNotFoundExceptionReturnsNotFound() {
        EntityNotFoundException ex = new EntityNotFoundException("Entity not found");
        WebRequest request = mock(WebRequest.class);

        ResponseEntity<?> response = handler.handleResourceNotFoundException(ex, request);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Entity not found", response.getBody());
    }

    @Test
    void handleEntityAlreadyExistsExceptionReturnsConflict() {
        EntityAlreadyExistsException ex = new EntityAlreadyExistsException("Entity already exists");
        WebRequest request = mock(WebRequest.class);

        ResponseEntity<?> response = handler.handleEntityAlreadyExistsException(ex, request);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Entity already exists", response.getBody());
    }
}