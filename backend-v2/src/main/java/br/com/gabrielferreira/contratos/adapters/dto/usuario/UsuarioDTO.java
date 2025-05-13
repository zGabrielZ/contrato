package br.com.gabrielferreira.contratos.adapters.dto.usuario;

import br.com.gabrielferreira.contratos.adapters.dto.perfil.PerfilDTO;
import br.com.gabrielferreira.contratos.adapters.dto.telefone.TelefoneDTO;
import lombok.Builder;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Builder
public record UsuarioDTO(
        UUID id,
        String nome,
        String sobrenome,
        String email,
        List<TelefoneDTO> telefones,
        List<PerfilDTO>  perfis,
        SaldoDTO saldo
) implements Serializable {
}
