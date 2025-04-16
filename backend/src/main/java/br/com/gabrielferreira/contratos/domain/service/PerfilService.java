package br.com.gabrielferreira.contratos.domain.service;

import br.com.gabrielferreira.contratos.domain.model.Perfil;

import java.util.List;

public interface PerfilService {

    Perfil buscarPorId(Long id);

    List<Perfil> consultar();
}
