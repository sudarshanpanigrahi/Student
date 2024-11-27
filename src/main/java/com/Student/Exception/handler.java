package com.Student.Exception;

import com.Student.Entity.errorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.naming.Binding;
import java.util.Date;

@RestControllerAdvice
public class handler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<errorDetails> get(Exception e, WebRequest request){
        errorDetails ed = new errorDetails(new Date(),e.getMessage(),request.getDescription(false));
        return  new ResponseEntity<>(ed, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(ResourceNotFound.class)
    public ResourceNotFound get(){
        return new ResourceNotFound("Resource not found");
    }
}
