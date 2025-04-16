package br.com.gabrielferreira.contratos.domain.service;

import br.com.gabrielferreira.contratos.domain.dao.filter.TelefoneFilterModel;
import br.com.gabrielferreira.contratos.domain.model.Telefone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TelefoneService {

    Telefone cadastrar(Long idUsuario, Telefone telefone);

    Telefone consultarTelefonePorId(Long idUsuario, Long id);

    Page<Telefone> consultar(Long idUsuario, Pageable pageable, TelefoneFilterModel filtro);

    Long buscarQuantidadePorUsuario(Long idUsuario);

    Telefone atualizar(Long idUsuario, Long id, Telefone telefone);

    void deletarPorId(Long idUsuario, Long id);
}
