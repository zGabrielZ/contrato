package br.com.gabrielferreira.contratos.domain.mapper;

import br.com.gabrielferreira.contratos.domain.model.SaldoTotalUsuario;
import org.mapstruct.Mapper;

import java.math.BigDecimal;

@Mapper(componentModel = "spring")
public interface CadastrarSaldoTotalUsuarioMapper {

    SaldoTotalUsuario createSaldoTotalUsuario(BigDecimal valor);
}
