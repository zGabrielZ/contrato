package br.com.gabrielferreira.contratos.command.impl.usuario;

import br.com.gabrielferreira.contratos.domain.command.impl.usuario.ConsultarUsuarioExistentePorIdCommandImpl;
import br.com.gabrielferreira.contratos.domain.repository.UsuarioRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ConsultarUsuarioExistentePorIdCommandImplTest {

    @InjectMocks
    private ConsultarUsuarioExistentePorIdCommandImpl consultarUsuarioExistentePorIdCommand;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    @Order(1)
    @DisplayName("Deve consultar usuário")
    void deveConsultarUsuario() {
        when(usuarioRepository.buscarUsuarioExistente(any()))
                .thenReturn(true);

        assertTrue(consultarUsuarioExistentePorIdCommand.execute(1L));
    }
}
