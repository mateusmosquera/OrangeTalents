package br.com.orangetalents.loteria.config;

import br.com.orangetalents.loteria.controller.dto.RespostaDto;
import br.com.orangetalents.loteria.controller.exceptions.EmailNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ErroDeValidacaoHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroValidacaoDto> handle(MethodArgumentNotValidException exception) {

        List<ErroValidacaoDto> dto = new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e ->{
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErroValidacaoDto erro = new ErroValidacaoDto(e.getField(),mensagem);
            dto.add(erro);
        });

        return dto;
    }

    @ResponseStatus(value = NOT_FOUND)
    @ExceptionHandler(EmailNaoEncontradaException.class)
    public @ResponseBody
    RespostaDto handleException(EmailNaoEncontradaException e) {
        return new RespostaDto(e.getMessage());
    }
}
