package org.example.exception;

import org.example.dto.ErrorResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();

    @Test
    public void whenHandleRecipeNotFoundException_thenReturnNotFound() {
        RecipeNotFound exception = new RecipeNotFound(1);
        WebRequest request = new ServletWebRequest(new MockHttpServletRequest());

        ResponseEntity<ErrorResponse> response = exceptionHandler.handleRecipeNotFoundException(exception, request);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getCode()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(response.getBody().getMessage()).isEqualTo("No recipe with the id 1");
    }

    @Test
    public void whenHandleConstraintViolationException_thenReturnBadRequest() {
        Set<ConstraintViolation<?>> violations = new HashSet<>();
        ConstraintViolation<?> violation = mock(ConstraintViolation.class);
        when(violation.getMessage()).thenReturn("must not be null");
        violations.add(violation);
        
        ConstraintViolationException exception = new ConstraintViolationException("Validation failed", violations);
        WebRequest request = new ServletWebRequest(new MockHttpServletRequest());

        ResponseEntity<ErrorResponse> response = exceptionHandler.handleConstraintViolationException(exception, request);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getBody().getMessage()).isEqualTo("Validation failed: must not be null");
    }

    @Test
    public void whenHandleMethodArgumentTypeMismatchException_thenReturnBadRequest() {
        MethodArgumentTypeMismatchException exception = mock(MethodArgumentTypeMismatchException.class);
        when(exception.getName()).thenReturn("id");
        when(exception.getRequiredType()).thenReturn((Class)Integer.class);
        
        WebRequest request = new ServletWebRequest(new MockHttpServletRequest());

        ResponseEntity<ErrorResponse> response = exceptionHandler.handleMethodArgumentTypeMismatchException(exception, request);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(response.getBody().getMessage()).isEqualTo("The parameter 'id' should be of type 'Integer'");
    }

    @Test
    public void whenHandleGlobalException_thenReturnInternalServerError() {
        Exception exception = new RuntimeException("Unexpected error");
        WebRequest request = new ServletWebRequest(new MockHttpServletRequest());

        ResponseEntity<ErrorResponse> response = exceptionHandler.handleGlobalException(exception, request);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR.value());
        assertThat(response.getBody().getMessage()).isEqualTo("Internal server error. Please try again later.");
    }
} 