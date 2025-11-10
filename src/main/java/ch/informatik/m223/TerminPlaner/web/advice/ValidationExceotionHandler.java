package ch.informatik.m223.TerminPlaner.web.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handle(MethodArgumentNotValidException ex)
    Map<String, String> errors = new LinkedHashMap<>();
    ex.getBindingResult().getFielderrors()
        .forEach(err -> errors.put(err.getField(), err.getDefaultMessage()));
            ex.getBindingResult().getGlobalErrors()
                .forEach(err -> errors.put(err.getObjectName(), err.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
          }
        }