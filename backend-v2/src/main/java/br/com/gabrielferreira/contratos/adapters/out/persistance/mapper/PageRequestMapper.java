package br.com.gabrielferreira.contratos.adapters.out.persistance.mapper;

import br.com.gabrielferreira.contratos.application.core.model.filtro.PageInfo;
import org.mapstruct.Mapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface PageRequestMapper {

    default PageRequest toPageRequest(PageInfo pageInfo) {
        return PageRequest.of(
                pageInfo.pageNumber(),
                pageInfo.pageSize(),
                toSort(pageInfo.sortBy())
        );
    }

    default Sort toSort(List<String[]> sortBy) {
        List<Sort.Order> orders = new ArrayList<>();
        if (!CollectionUtils.isEmpty(sortBy)) {
            sortBy.forEach(sort -> orders.add(new Sort.Order(Sort.Direction.fromString(sort[0]), sort[1])));
        }
        return Sort.by(orders);
    }
}
