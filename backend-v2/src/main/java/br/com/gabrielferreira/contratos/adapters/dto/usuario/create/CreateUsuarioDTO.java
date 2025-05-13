package br.com.gabrielferreira.contratos.adapters.dto.usuario.create;

import br.com.gabrielferreira.contratos.adapters.dto.usuario.IdPerfilDTO;
import lombok.Builder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Builder
public record CreateUsuarioDTO(
        String nome,
        String sobrenome,
        String email,
        String senha,
        List<CreateTelefoneDTO> telefones,
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
