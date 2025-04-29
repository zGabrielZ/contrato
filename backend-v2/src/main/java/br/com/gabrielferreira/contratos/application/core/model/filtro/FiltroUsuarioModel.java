package br.com.gabrielferreira.contratos.application.core.model.filtro;

import br.com.gabrielferreira.contratos.application.exception.RegraDeNegocioException;
import lombok.Builder;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Builder
public record FiltroUsuarioModel(
        UUID id,
        String nome,
        String sobrenome,
        String email,
        LocalDate dataCadastro,
        LocalDate dataAtualizacao,
        BigDecimal saldoTotalInicial,
        BigDecimal saldoTotalFinal
) implements Serializable {

    public boolean isIdExistente() {
        return this.id != null;
    }

    public boolean isNomeExistente() {
        return this.nome != null && !this.nome.isBlank();
    }

    public boolean isSobrenomeExistente() {
        return this.sobrenome != null && !this.sobrenome.isBlank();
    }

    public boolean isEmailExistente() {
        return this.email != null && !this.email.isBlank();
    }

    public boolean isDataCadastroExistente() {
        return this.dataCadastro != null;
    }

    public boolean isDataAtualizacaoExistente() {
        return this.dataAtualizacao != null;
    }

    public boolean isSaldoTotalInicialExistente() {
        return this.saldoTotalInicial != null;
    }

    public boolean isSaldoTotalFinalExistente() {
        return this.saldoTotalFinal != null;
    }

    public void validarSaldoInicial() {
        if (!this.isSaldoTotalInicialExistente() && this.isSaldoTotalFinalExistente()) {
            throw new RegraDeNegocioException("É necessário informar o saldo total inicial");
        }
    }

    public void validarSaldoFinal() {
        if (this.isSaldoTotalInicialExistente() && !this.isSaldoTotalFinalExistente()) {
            throw new RegraDeNegocioException("É necessário informar o saldo total final");
        }
    }

    public void validarSaldoInicialComFinal() {
        if (isSaldoTotalInicialExistente() && isSaldoTotalFinalExistente()
                && this.saldoTotalInicial().compareTo(this.saldoTotalFinal()) > 0) {
            throw new RegraDeNegocioException("Saldo total inicial é maior que o saldo total final");
        }
    }
}
