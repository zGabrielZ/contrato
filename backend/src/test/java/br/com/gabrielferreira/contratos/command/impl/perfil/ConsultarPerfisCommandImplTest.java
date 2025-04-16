package br.com.gabrielferreira.contratos.command.impl.perfil;

import br.com.gabrielferreira.contratos.domain.command.impl.perfil.ConsultarPerfisCommandImpl;
import br.com.gabrielferreira.contratos.domain.model.Perfil;
import br.com.gabrielferreira.contratos.domain.repository.PerfilRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static br.com.gabrielferreira.contratos.tests.PerfilFactory.criarPerfis;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ConsultarPerfisCommandImplTest {

    @InjectMocks
    private ConsultarPerfisCommandImpl consultarPerfisCommand;

    @Mock
    private PerfilRepository perfilRepository;

    @Test
    @DisplayName("Deve buscar uma lista de perfis quando existir dados")
    @Order(1)
    void deveBuscarUmaListaDePerfis() {
        when(perfilRepository.buscarPerfis()).thenReturn(criarPerfis());

        List<Perfil> perfis = consultarPerfisCommand.execute();

        assertFalse(perfis.isEmpty());
        assertEquals(1L, perfis.get(0).getId());
        assertEquals(2L, perfis.get(1).getId());
        verify(perfilRepository, times(1)).buscarPerfis();
    }
}
