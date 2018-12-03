package com.leveris.call.manager.exception.handling;

import com.leveris.call.manager.exception.ServiceException;
import com.leveris.call.manager.exception.ValidationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@ResponseBody
@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(BAD_REQUEST)
    public com.leveris.call.manager.model.Error handleValidationException(ValidationException exception) {
        return new com.leveris.call.manager.model.Error(exception.getMessage());
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(BAD_REQUEST)
    public com.leveris.call.manager.model.Error handleServiceException(ServiceException exception) {
        return new com.leveris.call.manager.model.Error(exception.getMessage());
    }
}
