package br.com.orangetalents.loteria.repository;

import br.com.orangetalents.loteria.modelo.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario,String> {

   Optional<Usuario> findByEmail(String email);

}
