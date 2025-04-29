package br.com.gabrielferreira.contratos.application.core.model.filtro;

import lombok.Builder;

import java.io.Serializable;
import java.util.List;

@Builder
public record PageInfo(
        Integer pageNumber,
        Integer pageSize,
        List<String[]> sortBy
) implements Serializable {
}
