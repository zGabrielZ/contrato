package br.com.gabrielferreira.contratos.tests;

import br.com.gabrielferreira.contratos.api.dto.request.PerfilIdDTO;
import br.com.gabrielferreira.contratos.domain.model.Perfil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Arrays;
import java.util.List;

public class PerfilFactory {

    private PerfilFactory() {
    }

    public static Perfil criarPerfil() {
        return Perfil.builder()
                .id(1L)
                .descricao("Teste perfil")
                .autoriedade("Teste autoriedade")
                .build();
    }

    public static List<Perfil> criarPerfis() {
        Perfil perfil1 = criarPerfil();

        Perfil perfil2 = criarPerfil();
        perfil2.setId(2L);

        return Arrays.asList(perfil1, perfil2);
    }

    public static Page<Perfil> criarPerfisPaginacao() {
        return new PageImpl<>(List.of(criarPerfil()));
    }

    public static List<PerfilIdDTO> criarPerfisIds() {
        PerfilIdDTO id1 = new PerfilIdDTO(1L);
        PerfilIdDTO id2 = new PerfilIdDTO(2L);

        return Arrays.asList(id1, id2);
    }
}
