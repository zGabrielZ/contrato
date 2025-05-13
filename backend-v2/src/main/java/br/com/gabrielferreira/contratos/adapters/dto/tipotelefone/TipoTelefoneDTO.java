package br.com.gabrielferreira.contratos.adapters.dto.tipotelefone;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record TipoTelefoneDTO(
        String descricao,
        String codigo
) implements Serializable {
}
