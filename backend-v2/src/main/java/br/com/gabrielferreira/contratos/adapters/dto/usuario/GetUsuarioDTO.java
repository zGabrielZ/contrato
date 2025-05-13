package br.com.gabrielferreira.contratos.adapters.dto.usuario;

import lombok.Builder;

import java.io.Serializable;
import java.util.UUID;

@Builder
public record GetUsuarioDTO(
        UUID id,
        String nome,
        String sobrenome,
        String email,
        SaldoDTO saldo
) implements Serializable {
}
