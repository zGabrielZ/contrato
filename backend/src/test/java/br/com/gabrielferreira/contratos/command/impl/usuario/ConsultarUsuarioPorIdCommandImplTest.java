package br.com.gabrielferreira.contratos.command.impl.usuario;

import br.com.gabrielferreira.contratos.domain.command.impl.usuario.ConsultarUsuarioPorIdCommandImpl;
import br.com.gabrielferreira.contratos.domain.repository.UsuarioRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static br.com.gabrielferreira.contratos.tests.UsuarioFactory.usuarioCriado;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ConsultarUsuarioPorIdCommandImplTest {

    @InjectMocks
    private ConsultarUsuarioPorIdCommandImpl consultarUsuarioPorIdCommand;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    @Order(1)
    @DisplayName("Deve consultar usuário")
    void deveConsultarUsuario() {
        when(usuarioRepository.buscarPorId(anyLong()))
                .thenReturn(Optional.of(usuarioCriado()));

        assertNotNull(consultarUsuarioPorIdCommand.execute(1L));
    }
}
