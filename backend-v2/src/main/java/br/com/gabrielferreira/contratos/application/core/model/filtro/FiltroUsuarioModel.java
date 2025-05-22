package br.com.gabrielferreira.contratos.application.core.model.filtro;

import br.com.gabrielferreira.contratos.application.exception.RegraDeNegocioException;
import lombok.Builder;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Objects;
import java.util.UUID;

@Builder
public record FiltroUsuarioModel(
        UUID id,
        String nome,
        String sobrenome,
        String email,
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

    public void validarOrdenacao(PageInfo pageInfo) {
        if (Objects.nonNull(pageInfo) && !CollectionUtils.isEmpty(pageInfo.sortBy())) {
            Iterator<String[]> iterator = pageInfo.sortBy().iterator();
            while (iterator.hasNext()) {
                String[] sort = iterator.next();
                String sortField = sort[1];
                if (sortField.equalsIgnoreCase("saldo.valor")
                        || sortField.equalsIgnoreCase("saldo")) {
                    sort[1] = "saldoTotal.valor";
                }

                // Ignorar campos que não sejam esses abaixos
                if (!(
                        sortField.equalsIgnoreCase("id")
                                || sortField.equalsIgnoreCase("nome")
                                || sortField.equalsIgnoreCase("sobrenome")
                                || sortField.equalsIgnoreCase("email")
                                || sortField.equalsIgnoreCase("saldo.valor")
                                || sortField.equalsIgnoreCase("saldo")
                        )) {
                    iterator.remove();
                }
            }

            if (CollectionUtils.isEmpty(pageInfo.sortBy())) {
                pageInfo.sortBy().add(new String[] {
                        "DESC",
                        "id"
                });
            }
        }
    }
}
