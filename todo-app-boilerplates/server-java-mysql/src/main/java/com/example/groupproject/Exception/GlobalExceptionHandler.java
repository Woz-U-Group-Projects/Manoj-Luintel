package com.example.groupproject.Exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.sql.SQLException;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<?> exception(AccessDeniedException ex){
        logger.info("Exception occurred :" + ex.getMessage());
        return new ResponseEntity<>("Access denied.",new HttpHeaders(), HttpStatus.FORBIDDEN);
    }


    @ExceptionHandler({BadRequestException.class})
    public ResponseEntity<?> exception(BadRequestException ex){
        logger.info("Exception occurred :" + ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({EntityAlreadyExistsException.class})
    public ResponseEntity<?> exception(EntityAlreadyExistsException ex){
        logger.info("Exception occurred :" + ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvNotFoundException.class})
    public ResponseEntity<?> exception(InvNotFoundException ex){
        logger.info("Exception occurred :" + ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({UserAlreadyExistsException.class})
    public ResponseEntity<?> exception(UserAlreadyExistsException ex){
        logger.info("Exception occurred :" + ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> exception(Exception ex){
        logger.info("Exception occurred :" + ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({SQLException.class})
    public ResponseEntity<?> exception(SQLException ex){
        logger.info("Exception occurred :" + ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvalidFormatException.class})
    public ResponseEntity<?> exception(InvalidFormatException ex){
        String[] messageParts = ex.getOriginalMessage().split(":");

        String stringBuilder = ex.getValue() +
                ": " +
                messageParts[messageParts.length - 1] +
                messageParts[messageParts.length - 2];
        logger.info("Exception occurred :" + ex.getMessage());
        return new ResponseEntity<>(new ErrorResponse(stringBuilder), HttpStatus.BAD_REQUEST);
    }
}
