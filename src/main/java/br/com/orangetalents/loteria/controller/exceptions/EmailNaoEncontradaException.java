package br.com.orangetalents.loteria.controller.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmailNaoEncontradaException extends Exception {

    public EmailNaoEncontradaException() {
        super("EMAIL NAO ENCONTRADO");
    }

}
