package br.com.orangetalents.loteria.controller.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
@Getter
@Setter
public class RespostaDto {

    private static final String R_MSG_EMPTY = "";

    private final String message;
    private final Timestamp localDate;

    public RespostaDto(final String message) {
        this.message = message == null ? RespostaDto.R_MSG_EMPTY : message;
        this.localDate = new Timestamp(System.currentTimeMillis());
    }

}
