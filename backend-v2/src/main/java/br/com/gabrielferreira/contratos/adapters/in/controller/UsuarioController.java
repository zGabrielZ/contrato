package br.com.gabrielferreira.contratos.adapters.in.controller;

import br.com.gabrielferreira.contratos.adapters.dto.perfil.PerfilDTO;
import br.com.gabrielferreira.contratos.adapters.dto.usuario.GetUsuarioDTO;
import br.com.gabrielferreira.contratos.adapters.dto.usuario.UsuarioDTO;
import br.com.gabrielferreira.contratos.adapters.dto.usuario.create.CreateUsuarioDTO;
import br.com.gabrielferreira.contratos.adapters.dto.usuario.filter.FilterUsuarioDTO;
import br.com.gabrielferreira.contratos.adapters.in.service.UsuarioApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@Tag(name = "Usuário", description = "Endpoints para realizar requisições de usuários")
@RestController
@RequestMapping("/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioApiService usuarioApiService;

    @Operation(
            summary = "Cadastrar usuário",
            description = "Cadastrar usuário"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Usuário cadastrado"
            )
    })
    @PostMapping
    public ResponseEntity<UsuarioDTO> cadastrar(@Valid @RequestBody CreateUsuarioDTO request) {
        UsuarioDTO usuario = usuarioApiService.cadastrar(request);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(usuario.id()).toUri();
        return ResponseEntity.created(uri).body(usuario);
    }

    @Operation(
            summary = "Buscar usuário por ID",
            description = "Buscar usuário por ID"
    )
    @GetMapping("/{id}")
    public ResponseEntity<GetUsuarioDTO> buscarPorId(
            @Parameter(
                    description = "ID do usuário",
                    example = "2de43b48-8f56-4741-ac33-5cf84ef1be7b",
                    required = true
            )
            @PathVariable UUID id) {
        GetUsuarioDTO usuario = usuarioApiService.buscarPorId(id);
        return ResponseEntity.ok(usuario);
    }

    @Operation(
            summary = "Buscar perfis do usuário",
            description = "Buscar perfis do usuário"
    )
    @GetMapping("/{id}/perfis")
    public ResponseEntity<List<PerfilDTO>> buscarPerfis(
            @Parameter(
                    description = "ID do usuário",
                    example = "2de43b48-8f56-4741-ac33-5cf84ef1be7b",
                    required = true
            )
            @PathVariable UUID id) {
        return ResponseEntity.ok(usuarioApiService.buscarPerfis(id));
    }

    @Operation(
            summary = "Buscar usuários",
            description = "Buscar usuários"
    )
    @GetMapping
    public ResponseEntity<Page<GetUsuarioDTO>> buscarUsuarios(
            @Parameter(
                    description = "Filtro de busca de usuários"
            )
            @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
            @Valid FilterUsuarioDTO filtro
    ) {
        List<GetUsuarioDTO> usuarios = usuarioApiService.buscar(pageable, filtro);
        return ResponseEntity.ok(
                new PageImpl<>(usuarios, pageable, usuarios.size())
        );
    }
}
