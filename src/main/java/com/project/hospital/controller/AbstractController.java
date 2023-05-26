package com.project.hospital.controller;

import com.project.hospital.model.DTOs.ErrorDTOs.ErrorDTO;
import com.project.hospital.model.exceptions.BadRequestException;
import com.project.hospital.model.exceptions.NotFoundException;
import com.project.hospital.model.exceptions.UnauthorizedException;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


public abstract class AbstractController {


    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleBadRequest(Exception e){
        return generateErrorDTO(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleConstraintViolationException(ConstraintViolationException e) {
        return generateErrorDTO(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        return generateErrorDTO(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ErrorDTO handleUnauthorized(Exception e){
        return generateErrorDTO(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleNotFound(Exception e){
        return generateErrorDTO(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDTO handleRest(Exception e){

        e.printStackTrace();
        return generateErrorDTO(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ErrorDTO generateErrorDTO(Object o, HttpStatus s){
        String errorMessage = "Error occurred: " + o.toString() + ", status: " + s.toString();
        return ErrorDTO.builder()
                .message(o)
                .time(LocalDateTime.now())
                .status(s.value())
                .build();
    }

    protected int getLoggedUserId(HttpSession s){
        if(s.getAttribute("LOGGED_ID") == null){
            throw new UnauthorizedException("You have to login!");
        }
        return (int) s.getAttribute("LOGGED_ID");
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorDTO handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return generateErrorDTO(errors, HttpStatus.BAD_REQUEST);
    }
}