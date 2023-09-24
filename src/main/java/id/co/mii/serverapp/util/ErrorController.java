package id.co.mii.serverapp.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ErrorController {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorDto> error(ConstraintViolationException exception) {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(new ErrorDto(exception.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorDto> error(ResponseStatusException exception) {
        return ResponseEntity
            .status(exception.getStatus())
            .body(new ErrorDto(exception.getReason(), exception.getStatus().value()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDto> error(Exception exception) {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(new ErrorDto("Internal server error: " + exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }
}
