package br.com.gabrielferreira.contratos.api.controller;

import br.com.gabrielferreira.contratos.api.dto.request.AtualizarUsuarioDTO;
import br.com.gabrielferreira.contratos.api.dto.request.CriarUsuarioDTO;
import br.com.gabrielferreira.contratos.api.dto.request.FiltroUsuarioDTO;
import br.com.gabrielferreira.contratos.api.dto.response.UsuarioDTO;
import br.com.gabrielferreira.contratos.api.dto.response.UsuarioResumidoDTO;
import br.com.gabrielferreira.contratos.api.mapper.UsuarioMapper;
import br.com.gabrielferreira.contratos.domain.model.Usuario;
import br.com.gabrielferreira.contratos.domain.dao.filter.UsuarioFilterModel;
import br.com.gabrielferreira.contratos.domain.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Tag(name = "Usuário Controller", description = "Endpoints para realizar requisições de usuários")
@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    private final UsuarioMapper usuarioMapper;

    @Operation(summary = "Cadastrar usuário")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Usuário cadastrado"
            )
    })
    @PostMapping
    public ResponseEntity<UsuarioDTO> cadastrarUsuario(@Valid @RequestBody CriarUsuarioDTO create) {
        Usuario usuarioCadastrado = usuarioService.cadastrar(usuarioMapper.toUsuario(create));
        UsuarioDTO usuarioDto = usuarioMapper.toUsuarioDto(usuarioCadastrado);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(usuarioDto.id()).toUri();
        return ResponseEntity.created(uri).body(usuarioDto);
    }

    @Operation(summary = "Buscar usuário")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuário encontrado"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorId(@PathVariable Long id) {
        Usuario usuario = usuarioService.consultarPorId(id);
        UsuarioDTO usuarioDto = usuarioMapper.toUsuarioDto(usuario);

        return ResponseEntity.ok().body(usuarioDto);
    }

    @Operation(summary = "Atualizar usuário")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuário atualizado"
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> atualizarUsuarioPorId(@PathVariable Long id, @Valid @RequestBody AtualizarUsuarioDTO update) {
        Usuario usuarioAtualizado = usuarioService.atualizar(id, usuarioMapper.toUsuario(update));
        UsuarioDTO usuarioDto = usuarioMapper.toUsuarioDto(usuarioAtualizado);

        return ResponseEntity.ok().body(usuarioDto);
    }

    @Operation(summary = "Deletar usuário")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Usuário deletado"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuarioPorId(@PathVariable Long id) {
        usuarioService.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar usuários")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Usuários encontrados"
            )
    })
    @GetMapping
    public ResponseEntity<Page<UsuarioResumidoDTO>> buscarUsuarios(@PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                                                                   @Valid FiltroUsuarioDTO filtro) {
        UsuarioFilterModel usuarioFilterModel = usuarioMapper.toUsuarioFilterModel(filtro);
        Page<Usuario> usuarios = usuarioService.consultar(pageable, usuarioFilterModel);
        Page<UsuarioResumidoDTO> usuarioResumidoModels = usuarioMapper.toUsuarioResumidoDtos(usuarios);

        return ResponseEntity.ok().body(usuarioResumidoModels);
    }
}
