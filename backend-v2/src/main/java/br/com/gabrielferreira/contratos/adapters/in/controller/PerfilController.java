package br.com.gabrielferreira.contratos.adapters.in.controller;

import br.com.gabrielferreira.contratos.adapters.dto.perfil.PerfilDTO;
import br.com.gabrielferreira.contratos.adapters.in.service.PerfilApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/perfis")
@RequiredArgsConstructor
public class PerfilController {

    private final PerfilApiService perfilApiService;

    @GetMapping("/{id}")
    public ResponseEntity<PerfilDTO> buscarPorId(@PathVariable UUID id) {
        return ResponseEntity.ok(perfilApiService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<PerfilDTO>> buscar() {
        return ResponseEntity.ok(perfilApiService.buscar());
    }
}
