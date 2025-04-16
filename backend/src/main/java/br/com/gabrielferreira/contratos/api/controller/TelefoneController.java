package br.com.gabrielferreira.contratos.api.controller;

import br.com.gabrielferreira.contratos.api.dto.request.CriarTelefoneDTO;
import br.com.gabrielferreira.contratos.api.dto.request.FiltroTelefoneDTO;
import br.com.gabrielferreira.contratos.api.dto.response.QuantidadeTelefoneDTO;
import br.com.gabrielferreira.contratos.api.dto.response.TelefoneDTO;
import br.com.gabrielferreira.contratos.api.mapper.TelefoneMapper;
import br.com.gabrielferreira.contratos.domain.dao.filter.TelefoneFilterModel;
import br.com.gabrielferreira.contratos.domain.model.Telefone;
import br.com.gabrielferreira.contratos.domain.service.TelefoneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

@RestController
@RequestMapping("/usuarios/{idUsuario}/telefones")
@RequiredArgsConstructor
public class TelefoneController {

    private final TelefoneService telefoneService;

    private final TelefoneMapper telefoneMapper;

    @Operation(summary = "Cadastrar telefone")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Telefone cadastrado"
            )
    })
    @PostMapping
    public ResponseEntity<TelefoneDTO> cadastrarTelefone(@PathVariable Long idUsuario, @Valid @RequestBody CriarTelefoneDTO create) {
        Telefone telefone = telefoneMapper.toTelefone(create);
        Telefone telefoneCadastrado = telefoneService.cadastrar(idUsuario, telefone);
        TelefoneDTO telefoneDto = telefoneMapper.toTelefoneDto(telefoneCadastrado);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(telefoneDto.id()).toUri();
        return ResponseEntity.created(uri).body(telefoneDto);
    }

    @Operation(summary = "Buscar telefone")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Telefone encontrado"
            )
    })
    @GetMapping("/{idTelefone}")
    public ResponseEntity<TelefoneDTO> buscarTelefonePorId(@PathVariable Long idUsuario, @PathVariable Long idTelefone) {
        Telefone telefone = telefoneService.consultarTelefonePorId(idUsuario, idTelefone);
        return ResponseEntity.ok().body(telefoneMapper.toTelefoneDto(telefone));
    }

    @Operation(summary = "Buscar quantidade de telefone por usuário")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Quantidade de telefone encontrado"
            )
    })
    @GetMapping("/quantidade")
    public ResponseEntity<QuantidadeTelefoneDTO> buscarQuantidadeTelefonesPorUsuario(@PathVariable Long idUsuario) {
        Long quantidadeTelefone = telefoneService.buscarQuantidadePorUsuario(idUsuario);
        return ResponseEntity.ok().body(telefoneMapper.toQuantidadeTelefoneDto(quantidadeTelefone));
    }

    @Operation(summary = "Buscar telefones por usuário")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Telefones encontrados"
            )
    })
    @GetMapping
    public ResponseEntity<Page<TelefoneDTO>> buscarTelefonesPorUsuario(@PathVariable Long idUsuario,
                                                                       @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                                                                       @Valid FiltroTelefoneDTO filtro) {
        TelefoneFilterModel telefoneFilterModel = telefoneMapper.toTelefoneFilterModel(filtro);
        Page<Telefone> telefones = telefoneService.consultar(idUsuario, pageable, telefoneFilterModel);
        return ResponseEntity.ok().body(telefoneMapper.toTelefoneDtos(telefones));
    }

    @Operation(summary = "Atualizar telefone")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Telefone atualizado"
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<TelefoneDTO> atualizarTelefonePorId(@PathVariable Long idUsuario,
                                                                @PathVariable Long id,
                                                                @Valid @RequestBody CriarTelefoneDTO update) {
        Telefone telefone = telefoneMapper.toTelefone(update);
        Telefone telefoneAtualizado = telefoneService.atualizar(idUsuario, id, telefone);
        return ResponseEntity.ok().body(telefoneMapper.toTelefoneDto(telefoneAtualizado));
    }

    @Operation(summary = "Deletar telefone")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Telefone deletado"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTelefonePorId(@PathVariable Long idUsuario, @PathVariable Long id){
        telefoneService.deletarPorId(idUsuario, id);
        return ResponseEntity.noContent().build();
    }
}
