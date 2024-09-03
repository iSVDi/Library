package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class AdviceController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Map<String, String> handleValidationErrors(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        List<ObjectError> allErrors = e.getBindingResult().getAllErrors();
        allErrors.forEach(err -> {
            FieldError fieldError = (FieldError)err;
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({HttpMessageNotReadableException.class})
    public Map<String, String> handleValidationErrors(HttpMessageNotReadableException e)  {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", e.getLocalizedMessage());
        return errors;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({LibraryException.class})
    public Map<String, String> handleValidationErrors(LibraryException e)  {
        Map<String, String> errors = new HashMap<>();
        errors.put("message", e.getLocalizedMessage());
        return errors;
    }
}
