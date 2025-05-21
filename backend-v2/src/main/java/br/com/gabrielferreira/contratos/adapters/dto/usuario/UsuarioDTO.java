package br.com.gabrielferreira.contratos.adapters.dto.usuario;

import br.com.gabrielferreira.contratos.adapters.dto.perfil.PerfilDTO;
import br.com.gabrielferreira.contratos.adapters.dto.telefone.TelefoneDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Builder
public record UsuarioDTO(
        @Schema(
                description = "ID do usuário",
                example = "1701dfbd-cfe2-417e-baab-bf93f8784e8f"
        )
        UUID id,

        @Schema(
                description = "Nome do usuário",
                example = "João"
        )
        String nome,

        @Schema(
                description = "Sobrenome do usuário",
                example = "Silva"
        )
        String sobrenome,

        @Schema(
                description = "E-mail do usuário",
                example = "joao@email.com.br"
        )
        String email,

        @Schema(
                description = "Lista de telefones do usuário"
        )
        List<TelefoneDTO> telefones,

        @Schema(
                description = "Lista de perfis do usuário"
        )
        List<PerfilDTO>  perfis,

        @Schema(
                description = "Saldo do usuário"
        )
        SaldoDTO saldo
) implements Serializable {
}
