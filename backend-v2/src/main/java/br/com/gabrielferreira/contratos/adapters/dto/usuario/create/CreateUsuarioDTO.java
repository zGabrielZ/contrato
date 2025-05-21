package br.com.gabrielferreira.contratos.adapters.dto.usuario.create;

import br.com.gabrielferreira.contratos.adapters.dto.usuario.IdPerfilDTO;
import br.com.gabrielferreira.contratos.adapters.validator.perfil.PerfilValid;
import br.com.gabrielferreira.contratos.adapters.validator.senha.SenhaValid;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Builder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Builder
public record CreateUsuarioDTO(
        @NotBlank
        @Size(max = 255, min = 1)
        @Schema(
                description = "Nome do usuário",
                example = "João"
        )
        String nome,

        @NotBlank
        @Size(max = 255, min = 1)
        @Schema(
                description = "Sobrenome do usuário",
                example = "Silva"
        )
        String sobrenome,

        @NotBlank
        @Email
        @Schema(
                description = "E-mail do usuário",
                example = "joao@email.com.br"
        )
        String email,

        @SenhaValid
        @NotBlank
        @Schema(
                description = "Senha do usuário",
                example = "senha123"
        )
        String senha,

        @Valid
        @Schema(
                description = "Lista de telefones do usuário"
        )
        List<CreateTelefoneDTO> telefones,

        @PerfilValid
        @Valid
        @NotEmpty
        @NotNull
        @Schema(
                description = "Lista de perfis do usuário"
        )
        List<IdPerfilDTO> perfis
) implements Serializable {

    @Override
    public List<CreateTelefoneDTO> telefones() {
        return telefones == null ? new ArrayList<>() : this.telefones;
    }

    @Override
    public List<IdPerfilDTO> perfis() {
        return perfis == null ? new ArrayList<>() : this.perfis;
    }
}
