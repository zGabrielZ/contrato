package br.com.gabrielferreira.contratos.adapters.out.persistance.specification;

import br.com.gabrielferreira.contratos.adapters.out.persistance.entity.UsuarioEntity;
import jakarta.persistence.criteria.JoinType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Component
public class UsuarioSpecification {

    private static final String SALDO_TOTAL = "saldoTotal";

    public Specification<UsuarioEntity> whereTrue() {
        return (root, query, criteriaBuilder) -> {
            if (Objects.nonNull(query)  && query.getResultType() != Long.class) {
                root.fetch(SALDO_TOTAL, JoinType.LEFT);
                root.fetch("perfis", JoinType.LEFT); // Evita N+1
                query.distinct(true); // Garante que a entidade raiz seja retornada
            }
            return criteriaBuilder.conjunction(); // Equivalente a WHERE 1=1
        };
    }

    public Specification<UsuarioEntity> findById(UUID id) {
        return (root, query, criteriaBuilder) -> {
            if (Objects.nonNull(id)) {
                return criteriaBuilder.equal(root.get("id"), id);
            }
            return null;
        };
    }

    public Specification<UsuarioEntity> findByNome(String nome) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isNotBlank(nome)) {
                return criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("nome")), "%" + nome.toLowerCase() + "%"
                );
            }
            return null;
        };
    }

    public Specification<UsuarioEntity> findBySobrenome(String sobrenome) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isNotBlank(sobrenome)) {
                return criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("sobrenome")), "%" + sobrenome.toLowerCase() + "%"
                );
            }
            return null;
        };
    }

    public Specification<UsuarioEntity> findByEmail(String email) {
        return (root, query, criteriaBuilder) -> {
            if (StringUtils.isNotBlank(email)) {
                return criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("email")), "%" + email.toLowerCase() + "%"
                );
            }
            return null;
        };
    }

    public Specification<UsuarioEntity> findBySaldoTotalInicial(BigDecimal saldoTotalInicial) {
        return (root, query, criteriaBuilder) -> {
            if (Objects.nonNull(saldoTotalInicial)) {
                return criteriaBuilder.greaterThanOrEqualTo(root.get(SALDO_TOTAL).get("valor"), saldoTotalInicial);
            }
            return null;
        };
    }

    public Specification<UsuarioEntity> findBySaldoTotalFinal(BigDecimal saldoTotalFinal) {
        return (root, query, criteriaBuilder) -> {
            if (Objects.nonNull(saldoTotalFinal)) {
                return criteriaBuilder.lessThanOrEqualTo(root.get(SALDO_TOTAL).get("valor"), saldoTotalFinal);
            }
            return null;
        };
    }
}
