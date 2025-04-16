package br.com.gabrielferreira.contratos.domain.command.telefone;

import br.com.gabrielferreira.contratos.domain.model.Telefone;
import br.com.gabrielferreira.contratos.domain.dao.filter.TelefoneFilterModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ConsultarTelefonesCommand {

    Page<Telefone> execute(Long idUsuario, Pageable pageable, TelefoneFilterModel filtro);
}
