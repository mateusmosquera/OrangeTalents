package br.com.orangetalents.loteria.modelo;


import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@Entity
public class Aposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ElementCollection
    private List<Integer> numeroSorteado;
    private LocalDateTime dataCriacao = LocalDateTime.now();
    @ManyToOne
    private Usuario usuario;

    public List<Integer> gerar(){
        Random random = new Random();
        List<Integer> generated = new ArrayList<>();
        for (int i=0;i<6;i++){
            int numero = random.nextInt(100)+1;
            while(generated.contains(numero)){
                numero = random.nextInt(100)+1;
            }
            generated.add(numero);
        }
        Collections.sort(generated);
        numeroSorteado =  generated;

        return numeroSorteado;
    }
}
