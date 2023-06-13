package project.departmentservice.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException{

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionRepresentation> handleEntityNotFoundException(EntityNotFoundException exception) {
        ExceptionRepresentation exceptionRepresentation =  ExceptionRepresentation.builder()
                .errorMessage(exception.getMessage())
                .errorCode("404")
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exceptionRepresentation);
    }
}
