package com.example.starwarsapi.starwarsapi.config;

import com.example.starwarsapi.starwarsapi.exceptions.ErrorValidation;
import com.example.starwarsapi.starwarsapi.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestControllerAdvice
public class ErrorHandle {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErrorValidation> handlerValidationError(MethodArgumentNotValidException exception){
        List<ErrorValidation> erros = new ArrayList<>();
        List<FieldError> camposErro = exception.getBindingResult().getFieldErrors();
        camposErro.forEach(e ->{
            String mensagem =  messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErrorValidation erro = new ErrorValidation(e.getField(), mensagem);
            erros.add(erro);
        });
        return  erros;
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public String handlerNotFound(NotFoundException exception){
        return exception.getMessage();
    }
}
