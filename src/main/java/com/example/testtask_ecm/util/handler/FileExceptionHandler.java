package com.example.testtask_ecm.util.handler;

import com.example.testtask_ecm.models.exception.ExceptionDTO;
import com.example.testtask_ecm.util.exception.EmptyUrlFieldException;
import com.example.testtask_ecm.util.exception.WrongPathException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestControllerAdvice
public class FileExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<ExceptionDTO> handleCommonHelper(final Exception exception, final HttpStatus status) {
        final var errorDto = new ExceptionDTO();
        errorDto.setMessage(exception.getMessage());
        return new ResponseEntity<>(errorDto, status);
    }

    @ExceptionHandler(value = {WrongPathException.class})
    public ResponseEntity<ExceptionDTO> handleWrongPath(final WrongPathException e) {
        return this.handleCommonHelper(e, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {EmptyUrlFieldException.class})
    public ResponseEntity<ExceptionDTO> handleEmptyUrl(final EmptyUrlFieldException e) {
        return this.handleCommonHelper(e, HttpStatus.NOT_FOUND);
    }
}
