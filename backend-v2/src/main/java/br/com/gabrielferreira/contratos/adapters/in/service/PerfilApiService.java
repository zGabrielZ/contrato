package br.com.gabrielferreira.contratos.adapters.in.service;

import br.com.gabrielferreira.contratos.adapters.dto.perfil.PerfilDTO;

import java.util.List;
import java.util.UUID;

public interface PerfilApiService {

    PerfilDTO buscarPorId(UUID id);

    List<PerfilDTO> buscar();
}
