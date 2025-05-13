package br.com.gabrielferreira.contratos.adapters.in.controller;

import br.com.gabrielferreira.contratos.adapters.dto.usuario.UsuarioDTO;
import br.com.gabrielferreira.contratos.adapters.dto.usuario.create.CreateUsuarioDTO;
import br.com.gabrielferreira.contratos.adapters.in.service.UsuarioApiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioApiService usuarioApiService;

    @PostMapping
    public ResponseEntity<UsuarioDTO> cadastrar(@Valid @RequestBody CreateUsuarioDTO request) {
        UsuarioDTO usuario = usuarioApiService.cadastrar(request);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(usuario.id()).toUri();
        return ResponseEntity.created(uri).body(usuario);
    }
}
