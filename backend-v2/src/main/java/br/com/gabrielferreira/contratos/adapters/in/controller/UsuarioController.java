package br.com.gabrielferreira.contratos.adapters.in.controller;

import br.com.gabrielferreira.contratos.adapters.dto.perfil.PerfilDTO;
import br.com.gabrielferreira.contratos.adapters.dto.usuario.GetUsuarioDTO;
import br.com.gabrielferreira.contratos.adapters.dto.usuario.UsuarioDTO;
import br.com.gabrielferreira.contratos.adapters.dto.usuario.create.CreateUsuarioDTO;
import br.com.gabrielferreira.contratos.adapters.in.service.UsuarioApiService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

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

    @GetMapping("/{id}")
    public ResponseEntity<GetUsuarioDTO> buscarPorId(@PathVariable UUID id) {
        GetUsuarioDTO usuario = usuarioApiService.buscarPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/{id}/perfis")
    public ResponseEntity<List<PerfilDTO>> buscarPerfis(@PathVariable UUID id) {
        return ResponseEntity.ok(usuarioApiService.buscarPerfis(id));
    }
}
