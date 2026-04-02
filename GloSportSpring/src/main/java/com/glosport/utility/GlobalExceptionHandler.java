package com.glosport.utility;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorInfo> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(
                error -> errors.put(error.getField(), error.getDefaultMessage())
        );

        ErrorInfo errorDetails = new ErrorInfo(
                "Request Body Validation Failed",
                errors.toString()
        );

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    //Add further Exception Handler methods for EquipmentException and PlayerException
    @ExceptionHandler(EquipmentException.class)
    public ResponseEntity<ErrorInfo> handleEquipmentException(EquipmentException ex) {
        String message = ex.getMessage();
        String title;
        if(message != null && message.toLowerCase().contains("not found")) {
            title = "Equipment Not Found";
        } else {
            title = "Equipment Request Failed";
        }
        ErrorInfo errorDetails = new ErrorInfo(title,message);
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PlayerException.class)
    public ResponseEntity<ErrorInfo> handlePlayerException(PlayerException ex){
        String message = ex.getMessage();
        HttpStatus status;
        String title;

        if (message != null && message.toLowerCase().contains("already registered")) {
            status=HttpStatus.CONFLICT;
            title = "Duplicate Player";
        } else if(message != null && message.toLowerCase().contains("not found")){
            status=HttpStatus.NOT_FOUND;
            title = "Player Request Failed";
        } else {
            status=HttpStatus.NOT_FOUND;
            title = "Player Request Failed";
        }
        ErrorInfo errorDetails = new ErrorInfo(title,message);
        return new ResponseEntity<>(errorDetails, status);
    }
}
