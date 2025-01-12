package com.example.projectmanager.exception;

import com.example.projectmanager.domain.ResponseMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ProjManagerExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public @ResponseBody ResponseMessage handleNotFoundMsg(NotFoundException notFoundException) {
        return new ResponseMessage("404",notFoundException.getMessage());
    }

    @ExceptionHandler(value = ProjectAllocationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ResponseMessage handleProjAllocException(ProjectAllocationException projectAllocationException) {
        return new ResponseMessage("400",projectAllocationException.getMessage());
    }
}
