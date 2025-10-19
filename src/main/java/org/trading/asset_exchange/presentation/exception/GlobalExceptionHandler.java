package org.trading.asset_exchange.presentation.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.trading.asset_exchange.presentation.response.ErrorResponse;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
      errors.put(error.getField(), error.getDefaultMessage());
    }

    // Build an error response
    ErrorResponse errorResponse = new ErrorResponse(
        "Validation Failed",
        errors.toString(), // Includes all field validation errors
        LocalDateTime.now(),
        HttpStatus.BAD_REQUEST.name()
    );
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }


  @ExceptionHandler(value = {IllegalArgumentException.class,
      MissingServletRequestParameterException.class})
  public ResponseEntity<ErrorResponse> handleIllegalArgumentException(Exception ex) {
    log.warn("Invalid argument passed: {}", ex.getMessage(),ex);
    ErrorResponse errorResponse = new ErrorResponse(
        ex.getMessage(),
        "Invalid argument passed.",
        LocalDateTime.now(),
        HttpStatus.BAD_REQUEST.name()
    );
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = {UnsupportedClassVersionError.class})
  public ResponseEntity<ErrorResponse> handleIllegalArgumentException(UnsupportedClassVersionError ex) {
    ErrorResponse errorResponse = new ErrorResponse(
        ex.getMessage(),
        "Invalid argument passed.",
        LocalDateTime.now(),
        HttpStatus.BAD_REQUEST.name()
    );
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
    log.error("Global exception occurred: {}", ex.getMessage(), ex);
    ErrorResponse errorResponse = new ErrorResponse(
        "An unexpected error occurred.",
        ex.getMessage(),
        LocalDateTime.now(),
        HttpStatus.INTERNAL_SERVER_ERROR.name()
    );
    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
