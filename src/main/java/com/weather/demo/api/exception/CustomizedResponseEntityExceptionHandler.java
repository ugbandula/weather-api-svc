package com.weather.demo.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

/**
 * Implements the RESTful controller advise.
 *
 * @author Bandula Gamage
 */

@RestControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles the Internal Server Error type exceptions.
     * @param ex		{@link Exception}
     * @param request	{@link WebRequest}
     * @return			{@link ResponseEntity}
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(true));
        return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles the {@link ResourceNotFoundException} exceptions.
     * @param ex 		{@link Exception}
     * @param request	{@link WebRequest}
     * @return			{@link ResponseEntity}
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        System.out.println("ResourceNotFoundException: " + ex.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(true));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Handles the {@link BadRequestException} exceptions.
     * @param ex 		{@link Exception}
     * @param request	{@link WebRequest}
     * @return			{@link ResponseEntity}
     */
    @ExceptionHandler(BadRequestException.class)
    public final ResponseEntity<ExceptionResponse> handleBadRequestException(BadRequestException ex, WebRequest request) {
        System.out.println("BadRequestException: " + ex.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(true), "false");
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles the {@link AccessDeniedException} exceptions.
     * @param ex 		{@link Exception}
     * @param request	{@link WebRequest}
     * @return			{@link ResponseEntity}
     */
    @ExceptionHandler(AccessDeniedException.class)
    public final ResponseEntity<ExceptionResponse> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(true), "false");
        return new ResponseEntity<>(exceptionResponse, HttpStatus.UNAUTHORIZED);
    }

}
