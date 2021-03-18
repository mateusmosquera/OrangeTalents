package br.com.orangetalents.loteria.config;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErroValidacaoDto {
    private String campo;
    private String mensagem;

    public ErroValidacaoDto(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }
}

