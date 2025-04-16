package br.com.gabrielferreira.contratos.domain.service.impl;

import br.com.gabrielferreira.contratos.domain.command.perfil.ConsultarPerfilPorIdCommand;
import br.com.gabrielferreira.contratos.domain.command.perfil.ConsultarPerfisCommand;
import br.com.gabrielferreira.contratos.domain.model.Perfil;
import br.com.gabrielferreira.contratos.domain.service.PerfilService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PerfilServiceImpl implements PerfilService {

    private final ConsultarPerfilPorIdCommand consultarPerfilPorIdCommand;

    private final ConsultarPerfisCommand consultarPerfisCommand;

    @Override
    public Perfil buscarPorId(Long id) {
        return consultarPerfilPorIdCommand.execute(id);
    }

    @Override
    public List<Perfil> consultar() {
        return consultarPerfisCommand.execute();
    }
}
