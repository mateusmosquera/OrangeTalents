package br.com.orangetalents.loteria.modelo;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    @OneToMany
    private List<Aposta> apostas;

    public Usuario(){
        apostas = new ArrayList<Aposta>();
    }

}
