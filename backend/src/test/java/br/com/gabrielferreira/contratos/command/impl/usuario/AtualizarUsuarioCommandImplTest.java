package br.com.gabrielferreira.contratos.command.impl.usuario;

import br.com.gabrielferreira.contratos.domain.command.impl.usuario.AtualizarUsuarioCommandImpl;
import br.com.gabrielferreira.contratos.domain.command.usuario.ConsultarUsuarioPorIdCommand;
import br.com.gabrielferreira.contratos.domain.model.Usuario;
import br.com.gabrielferreira.contratos.domain.repository.UsuarioRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static br.com.gabrielferreira.contratos.tests.UsuarioFactory.atualizarUsuario;
import static br.com.gabrielferreira.contratos.tests.UsuarioFactory.usuarioCriado;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AtualizarUsuarioCommandImplTest {

    @InjectMocks
    private AtualizarUsuarioCommandImpl atualizarUsuarioCommand;

    @Mock
    private ConsultarUsuarioPorIdCommand consultarUsuarioPorIdCommand;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Test
    @Order(1)
    @DisplayName("Deve atualizar usuário")
    void deveAtualizarUsuario() {
        Usuario usuario = atualizarUsuario();
        Long id = 1L;

        when(consultarUsuarioPorIdCommand.execute(anyLong()))
                .thenReturn(usuarioCriado());
        when(usuarioRepository.saveAndFlush(any()))
                .thenAnswer(invocacao -> {
                    Usuario usuarioPassado = invocacao.getArgument(0, Usuario.class);
                    usuarioPassado.setNome(usuario.getNome());
                    usuarioPassado.setSobrenome(usuario.getSobrenome());
                    return usuarioPassado;
                });

        Usuario usuarioAtualizado = atualizarUsuarioCommand.execute(id, usuario);
        assertNotNull(usuarioAtualizado);
        assertEquals(usuario.getNome(), usuarioAtualizado.getNome());
        assertEquals(usuario.getSobrenome(), usuarioAtualizado.getSobrenome());
    }
}
