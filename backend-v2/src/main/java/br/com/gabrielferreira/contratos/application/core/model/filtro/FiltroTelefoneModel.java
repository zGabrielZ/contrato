package br.com.gabrielferreira.contratos.application.core.model.filtro;

import br.com.gabrielferreira.contratos.application.core.enums.TipoTelefoneEnum;
import lombok.Builder;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Builder
public record FiltroTelefoneModel(
        UUID id,
        String ddd,
        String numero,
        String descricao,
        TipoTelefoneEnum tipoTelefone,
        LocalDate dataCadastro,
        LocalDate dataAtualizacao
) implements Serializable {

    public boolean isIdExistente() {
        return id != null;
    }

    public boolean isDddExistente() {
        return ddd != null && !ddd.isBlank();
    }

    public boolean isNumeroExistente() {
        return numero != null && !numero.isBlank();
    }

    public boolean isDescricaoExistente() {
        return descricao != null && !descricao.isBlank();
    }

    public boolean isTipoTelefoneExistente() {
        return tipoTelefone != null;
    }

    public boolean isDataCadastroExistente() {
        return dataCadastro != null;
    }

    public boolean isDataAtualizacaoExistente() {
        return dataAtualizacao != null;
    }
}
