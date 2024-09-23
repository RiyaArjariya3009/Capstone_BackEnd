package com.restaurant.RestaurantMicroservice.exception;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler for handling exceptions across the whole application.
 * <p>
 * This class is annotated with {@link RestControllerAdvice}, and it contains various
 * {@link ExceptionHandler} methods to handle different types of exceptions and return
 * appropriate responses to the client.
 * </p>
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles validation exceptions thrown when method arguments fail validation.
     *
     * @param ex the {@link MethodArgumentNotValidException} exception
     * @return a {@link ResponseEntity} containing the validation errors and a BAD_REQUEST status
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
     * Handles binding exceptions during data binding.
     *
     * @param ex the {@link BindException} exception
     * @return a {@link ResponseEntity} containing the binding errors and a BAD_REQUEST status
     */
    @ExceptionHandler(BindException.class)
    public ResponseEntity<Map<String, String>> handleBindException(BindException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles custom {@link ConflictException} when a conflict occurs.
     *
     * @param ex the {@link ConflictException} exception
     * @return a {@link ResponseEntity} containing the error response and a CONFLICT status
     */
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponse> handleConflictException(ConflictException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    /**
     * Handles unauthorized access exceptions.
     *
     * @param ex the {@link UnauthorizedException} exception
     * @return a {@link ResponseEntity} containing the error response and an UNAUTHORIZED status
     */
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> handleUnauthorizedException(UnauthorizedException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.UNAUTHORIZED.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handles restaurant creation exceptions.
     *
     * @param ex the {@link RestaurantCreationException} exception
     * @return a {@link ResponseEntity} containing the error response and a CONFLICT status
     */
    @ExceptionHandler(RestaurantCreationException.class)
    public ResponseEntity<ErrorResponse> handleRestaurantCreationException(RestaurantCreationException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    /**
     * Handles failed requests caused by internal server errors.
     *
     * @param ex the {@link FailedRequestException} exception
     * @return a {@link ResponseEntity} containing the error response and an INTERNAL_SERVER_ERROR status
     */
    @ExceptionHandler(FailedRequestException.class)
    public ResponseEntity<ErrorResponse> handleFailedRequestException(FailedRequestException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles image processing failures.
     *
     * @param ex the {@link ImageProcessingFailedException} exception
     * @return a {@link ResponseEntity} containing the error response and an INTERNAL_SERVER_ERROR status
     */
    @ExceptionHandler(ImageProcessingFailedException.class)
    public ResponseEntity<ErrorResponse> handleImageProcessingFailedException(ImageProcessingFailedException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles invalid file type exceptions.
     *
     * @param ex the {@link InvalidFileTypeException} exception
     * @return a {@link ResponseEntity} containing the error response and an UNSUPPORTED_MEDIA_TYPE status
     */
    @ExceptionHandler(InvalidFileTypeException.class)
    public ResponseEntity<ErrorResponse> handleInvalidFileTypeException(InvalidFileTypeException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(),
                ex.getMessage()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
    }

    /**
     * Handles not found exceptions and returns a simple error message.
     *
     * @param ex the {@link NotFoundException} exception
     * @return a {@link ResponseEntity} containing the error message and a NOT_FOUND status
     */
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles Feign client exceptions and extracts the message from the response body.
     *
     * @param ex the {@link FeignException} exception
     * @return a {@link ResponseEntity} containing the error response and an appropriate status
     */
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ErrorResponse> handleFeignException(FeignException ex) {
        HttpStatus status = HttpStatus.resolve(ex.status());
        if (status == null) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        String message = "An error occurred while processing your request";

        String feignResponseBody = ex.contentUTF8();  // Extracting the body of the Feign response
        if (feignResponseBody != null) {
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(feignResponseBody);  // Parse the response body
                message = jsonNode.get("message").asText();  // Extract the message field from the JSON
            } catch (Exception e) {
                message = ex.getMessage();  // Fallback to the default Feign exception message
            }
        }

        // Returning the response with extracted status and message
        return new ResponseEntity<>(new ErrorResponse(status.value(), message), status);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Please ensure the JSON is properly formatted and contains all required fields.");
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
