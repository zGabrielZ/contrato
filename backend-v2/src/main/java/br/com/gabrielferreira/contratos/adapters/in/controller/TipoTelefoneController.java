package br.com.gabrielferreira.contratos.adapters.in.controller;

import br.com.gabrielferreira.contratos.adapters.dto.tipotelefone.TipoTelefoneDTO;
import br.com.gabrielferreira.contratos.adapters.in.service.TipoTelefoneApiService;
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

@Tag(name = "Tipo Telefone", description = "Endpoints para realizar requisições de tipos de telefone")
@RestController
@RequestMapping("/v1/tipos-telefones")
@RequiredArgsConstructor
public class TipoTelefoneController {

    private final TipoTelefoneApiService tipoTelefoneApiService;

    @Operation(
            summary = "Buscar tipo de telefone por código",
            description = "Buscar tipo de telefone por código"
    )
    @GetMapping("/{codigo}")
    public ResponseEntity<TipoTelefoneDTO> buscarPorCodigo(
            @Parameter(
                    description = "Código do tipo de telefone",
                    example = "CELULAR",
                    required = true
            )
            @PathVariable String codigo) {
        return ResponseEntity.ok(tipoTelefoneApiService.buscarTipoTelefonePorCodigo(codigo));
    }

    @Operation(
            summary = "Buscar todos os tipos de telefone",
            description = "Buscar todos os tipos de telefone"
    )
    @GetMapping
    public ResponseEntity<List<TipoTelefoneDTO>> buscar() {
        return ResponseEntity.ok(tipoTelefoneApiService.buscar());
    }
}
