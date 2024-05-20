package com.caminhosdofrio.exceptions.servico_turistico;

import com.caminhosdofrio.dtos.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ServicoTuristicoExceptionHandler {
    @ExceptionHandler(ServicoTuristicoNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleServicoTuristicoNotFound(ServicoTuristicoNotFoundException ex) {
        return new ErrorDTO(
                HttpStatus.NOT_FOUND.value(),
                "Servico Turistico não encontrado",
                LocalDateTime.now()
        );
    }
}
