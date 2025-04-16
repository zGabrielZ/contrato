package br.com.gabrielferreira.contratos.command.impl.perfil;

import br.com.gabrielferreira.contratos.domain.command.impl.perfil.ConsultarPerfilPorIdCommandImpl;
import br.com.gabrielferreira.contratos.domain.exception.NaoEncontradoException;
import br.com.gabrielferreira.contratos.domain.model.Perfil;
import br.com.gabrielferreira.contratos.domain.repository.PerfilRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static br.com.gabrielferreira.contratos.tests.PerfilFactory.criarPerfil;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ConsultarPerfilPorIdCommandImplTest {

    @InjectMocks
    private ConsultarPerfilPorIdCommandImpl consultarPerfilPorIdCommand;

    @Mock
    private PerfilRepository perfilRepository;

    private Long idPerfilExistente;

    private Long idPerfilInexistente;

    @BeforeEach
    void setUp(){
        idPerfilExistente = 1L;
        idPerfilInexistente = -1L;
    }

    @Test
    @DisplayName("Deve buscar perfil por id")
    @Order(1)
    void deveBuscarPerfil() {
        when(perfilRepository.findById(idPerfilExistente)).thenReturn(Optional.of(criarPerfil()));

        Perfil perfil = consultarPerfilPorIdCommand.execute(idPerfilExistente);

        assertNotNull(perfil);
        assertEquals(1L, perfil.getId());
        verify(perfilRepository, times(1)).findById(idPerfilExistente);
    }

    @Test
    @DisplayName("Não deve buscar perfil por id quando não encontrar")
    @Order(2)
    void naoDeveBuscarPerfil() {
        when(perfilRepository.findById(idPerfilInexistente)).thenReturn(Optional.empty());

        assertThrows(NaoEncontradoException.class, () -> consultarPerfilPorIdCommand.execute(idPerfilInexistente));
        verify(perfilRepository, times(1)).findById(idPerfilInexistente);
    }
}
