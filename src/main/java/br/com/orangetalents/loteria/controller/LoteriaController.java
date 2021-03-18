package br.com.orangetalents.loteria.controller;

import br.com.orangetalents.loteria.controller.dto.ApostaDto;
import br.com.orangetalents.loteria.controller.exceptions.EmailNaoEncontradaException;
import br.com.orangetalents.loteria.form.UsuarioForm;
import br.com.orangetalents.loteria.service.LoteriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;


@Controller
@RequestMapping(value = "/loteria")
public class LoteriaController {

    @Autowired
    LoteriaService loteriaService;

    @PostMapping
    @Transactional
    public ResponseEntity<ApostaDto> registarAposta(@RequestBody @Valid UsuarioForm userForm, UriComponentsBuilder uriBuilder) {
        String email = userForm.getEmail();

        ApostaDto apostaDto = loteriaService.registrarAposta(email);

        URI uri = uriBuilder.path("/loteria/listar/").queryParam("email",email).buildAndExpand().toUri();

        return ResponseEntity.created(uri).body(apostaDto);
    }

    @GetMapping("/listar")
    public ResponseEntity<Page<ApostaDto>> listarApostasDoUsuario(@RequestParam("email") String email,
                                                                  @PageableDefault(sort = "dataCriacao", direction = Sort.Direction.ASC, page = 0, size = 10)
                                                                          Pageable paginacao)
                                                                  throws EmailNaoEncontradaException {

        return ResponseEntity.ok().body(loteriaService.listarApostasUsuario(email,paginacao));
    }
}
