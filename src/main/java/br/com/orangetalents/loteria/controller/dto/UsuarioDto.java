package br.com.orangetalents.loteria.controller.dto;

import br.com.orangetalents.loteria.modelo.Aposta;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class UsuarioDto {

    private String email;
    private List<Aposta> apostas;
    private LocalDateTime dataDeCriacao;

}
