package br.com.orangetalents.loteria.service;


import br.com.orangetalents.loteria.controller.dto.ApostaDto;
import br.com.orangetalents.loteria.controller.exceptions.EmailNaoEncontradaException;
import br.com.orangetalents.loteria.modelo.Aposta;
import br.com.orangetalents.loteria.modelo.Usuario;
import br.com.orangetalents.loteria.repository.ApostaRepository;
import br.com.orangetalents.loteria.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LoteriaService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ApostaRepository apostaRepository;

    public ApostaDto registrarAposta(String email){
        Usuario user;
        if(usuarioRepository.findByEmail(email).isPresent()){
            user = usuarioRepository.findByEmail(email).get();
        }else{
            user = new Usuario();
            user.setEmail(email);
        }

        Aposta aposta = new Aposta();

        user.getApostas().add(gerarApostaValida(aposta,user));

        aposta.setUsuario(user);

        apostaRepository.save(aposta);

        usuarioRepository.save(user);

        ApostaDto apostaDto = new ApostaDto(aposta);

        return apostaDto;
    }

    public Aposta gerarApostaValida(Aposta aposta,Usuario usuario){
        while(usuario.getApostas().contains(aposta) || aposta.getNumeroSorteado()==null) {
            aposta.gerar();
        }
        return aposta;
    }

    public Page<ApostaDto> listarApostasUsuario(String email, Pageable paginaçao) throws EmailNaoEncontradaException {

        if (!usuarioRepository.findByEmail(email).isPresent()){
            throw new EmailNaoEncontradaException();
        }
        Page<Aposta> apostas = apostaRepository.findAllApostaByUsuarioEmail(email,paginaçao);

        Page<ApostaDto> apostasDto = ApostaDto.converter(apostas);

        return apostasDto;
    }


}
