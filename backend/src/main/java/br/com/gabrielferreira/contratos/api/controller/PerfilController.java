package br.com.gabrielferreira.contratos.api.controller;

import br.com.gabrielferreira.contratos.api.dto.response.PerfilDTO;
import br.com.gabrielferreira.contratos.api.mapper.PerfilMapper;
import br.com.gabrielferreira.contratos.domain.model.Perfil;
import br.com.gabrielferreira.contratos.domain.service.PerfilService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/perfis")
@RequiredArgsConstructor
public class PerfilController {

    private final PerfilService perfilService;

    private final PerfilMapper perfilMapper;

    @Operation(summary = "Buscar perfis")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Perfis encontrados"
            )
    })
    @GetMapping
    public ResponseEntity<List<PerfilDTO>> buscarPerfis() {
        List<Perfil> perfis = perfilService.consultar();
        return ResponseEntity.ok().body(perfilMapper.toPerfilDtos(perfis));
    }

    @Operation(summary = "Buscar perfil")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Perfil encontrado"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<PerfilDTO> buscarPerfilPorId(@PathVariable Long id) {
        Perfil perfil = perfilService.buscarPorId(id);
        return ResponseEntity.ok().body(perfilMapper.toPerfilDto(perfil));
    }
}
