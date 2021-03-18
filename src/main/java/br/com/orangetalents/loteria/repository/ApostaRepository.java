package br.com.orangetalents.loteria.repository;

import br.com.orangetalents.loteria.modelo.Aposta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;


public interface ApostaRepository  extends CrudRepository<Aposta,String> {

    Page<Aposta> findAllApostaByUsuarioEmail(String email, Pageable paginacao);

}
