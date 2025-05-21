package br.com.gabrielferreira.contratos.adapters.in.controller;

import br.com.gabrielferreira.contratos.adapters.dto.perfil.PerfilDTO;
import br.com.gabrielferreira.contratos.adapters.in.service.PerfilApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Tag(name = "Perfil", description = "Endpoints para realizar requisições de perfis")
@RestController
@RequestMapping("/v1/perfis")
@RequiredArgsConstructor
public class PerfilController {

    private final PerfilApiService perfilApiService;

    @Operation(
            summary = "Buscar perfil por ID",
            description = "Buscar perfil por ID"
    )
    @GetMapping("/{id}")
    public ResponseEntity<PerfilDTO> buscarPorId(
            @Parameter(
                    description = "ID do perfil",
                    example = "d2f3c4b5-6e7f-8a9b-0c1d-e2f3g4h5i6j7",
                    required = true
            )
            @PathVariable UUID id)
    {
        return ResponseEntity.ok(perfilApiService.buscarPorId(id));
    }

    @Operation(
            summary = "Buscar todos os perfis",
            description = "Buscar todos os perfis"
    )
    @GetMapping
    public ResponseEntity<List<PerfilDTO>> buscar() {
        return ResponseEntity.ok(perfilApiService.buscar());
    }
}
