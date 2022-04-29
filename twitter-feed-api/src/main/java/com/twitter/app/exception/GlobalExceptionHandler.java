package com.twitter.app.exception;

import static org.springframework.web.client.HttpServerErrorException.InternalServerError;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;

/**
 * @author davidjmartin
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DataAccessException.class)
    @ResponseStatus(code = HttpStatus.CONFLICT)
    @ResponseBody
    public ErrorData handleDataAccessException(HttpServletRequest request, DataAccessException ex) {
        log.debug("handling DataAccessException: {}.", ex.getMessage());
        return buildErrorData("encountered exception.", ex.getMessage(), request);
    }

    @ExceptionHandler(value = InternalServerError.class)
    @ResponseStatus(code = HttpStatus.CONFLICT)
    @ResponseBody
    ErrorData handleInternalServerError(InternalServerError exception, HttpServletRequest request) {
        log.debug("Handling exception: InternalServerError.");
        return buildErrorData("500", "encountered internal server error.", request);
    }

    private ErrorData buildErrorData(String errorCode, String message, HttpServletRequest request) {
        return ErrorData.builder()
            .errorCode(errorCode)
            .message(message)
            .url(request.getMethod() + " request to : " + request.getRequestURI())
            .timestamp(LocalDateTime.now().toString())
            .build();
    }

}