package br.com.gabrielferreira.contratos.adapters.in.controller;

import br.com.gabrielferreira.contratos.adapters.dto.tipotelefone.TipoTelefoneDTO;
import br.com.gabrielferreira.contratos.adapters.in.service.TipoTelefoneApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/tipos-telefones")
@RequiredArgsConstructor
public class TipoTelefoneController {

    private final TipoTelefoneApiService tipoTelefoneApiService;

    @GetMapping("/{codigo}")
    public ResponseEntity<TipoTelefoneDTO> buscarPorCodigo(@PathVariable String codigo) {
        return ResponseEntity.ok(tipoTelefoneApiService.buscarTipoTelefonePorCodigo(codigo));
    }

    @GetMapping
    public ResponseEntity<List<TipoTelefoneDTO>> buscar() {
        return ResponseEntity.ok(tipoTelefoneApiService.buscar());
    }
}
