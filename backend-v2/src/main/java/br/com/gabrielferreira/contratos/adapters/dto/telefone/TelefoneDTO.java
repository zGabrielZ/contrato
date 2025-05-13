package br.com.gabrielferreira.contratos.adapters.dto.telefone;

import br.com.gabrielferreira.contratos.adapters.dto.tipotelefone.TipoTelefoneDTO;
import lombok.Builder;

import java.io.Serializable;
import java.util.UUID;

@Builder
public record TelefoneDTO(
        UUID id,
        String ddd,
        String numero,
        String descricao,
        TipoTelefoneDTO tipoTelefone
) implements Serializable {
}
