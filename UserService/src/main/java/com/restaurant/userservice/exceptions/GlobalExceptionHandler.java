package com.restaurant.userservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler for handling various exceptions and generating appropriate error responses.
 * This class is designed to handle exceptions thrown by the application and return a standardized error response.
 */
@RestControllerAdvice
public final class GlobalExceptionHandler {

    /**
     * Handles UserNotFoundException and returns a standardized error response with HTTP status 404.
     *
     * @param ex the UserNotFoundException
     * @return a ResponseEntity containing the error response
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles UserExistsException and returns a standardized error response with HTTP status 409.
     *
     * @param ex the UserExistsException
     * @return a ResponseEntity containing the error response
     */
    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserExistsException(UserExistsException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    /**
     * Handles InvalidCredentialsException and returns a standardized error response with HTTP status 401.
     *
     * @param ex the InvalidCredentialsException
     * @return a ResponseEntity containing the error response
     */
    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentialsException(InvalidCredentialsException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.UNAUTHORIZED.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handles MethodArgumentNotValidException and returns a map of validation errors with HTTP status 400.
     *
     * @param ex the MethodArgumentNotValidException
     * @return a ResponseEntity containing a map of validation errors
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles InvalidRoleException and returns a standardized error response with HTTP status 403.
     *
     * @param ex the InvalidRoleException
     * @return a ResponseEntity containing the error response
     */
    @ExceptionHandler(InvalidRoleException.class)
    public ResponseEntity<ErrorResponse> handleInvalidRoleException(InvalidRoleException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.FORBIDDEN.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }

    /**
     * Handles InvalidEmailDomainException and returns the error message with HTTP status 400.
     *
     * @param ex the InvalidEmailDomainException
     * @return a ResponseEntity containing the error message
     */
    @ExceptionHandler(InvalidEmailDomainException.class)
    public ResponseEntity<String> handleInvalidEmailDomainException(InvalidEmailDomainException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles AddressNotFoundExceptions and returns a standardized error response with HTTP status 404.
     *
     * @param ex the AddressNotFoundExceptions
     * @return a ResponseEntity containing the error response
     */
    @ExceptionHandler(AddressNotFoundExceptions.class)
    public ResponseEntity<ErrorResponse> handleAddressNotFoundException(AddressNotFoundExceptions ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles UserNotAllowedExceptions and returns a standardized error response with HTTP status 403.
     *
     * @param ex the UserNotAllowedExceptions
     * @return a ResponseEntity containing the error response
     */
    @ExceptionHandler(UserNotAllowedExceptions.class)
    public ResponseEntity<ErrorResponse> handleUserNotAllowedException(UserNotAllowedExceptions ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.FORBIDDEN.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.FORBIDDEN);
    }
}
