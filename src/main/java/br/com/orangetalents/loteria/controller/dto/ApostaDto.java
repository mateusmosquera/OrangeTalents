package br.com.orangetalents.loteria.controller.dto;

import br.com.orangetalents.loteria.modelo.Aposta;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ApostaDto {
    private List<Integer> numeroSorteado;
    private LocalDateTime dataCriacao;

    public ApostaDto() {

    }

    public ApostaDto(Aposta aposta) {
        BeanUtils.copyProperties(aposta, this);
    }

    public static Page<ApostaDto> converter(Page<Aposta> apostas) {
        return apostas.map(ApostaDto::new);
    }
}
