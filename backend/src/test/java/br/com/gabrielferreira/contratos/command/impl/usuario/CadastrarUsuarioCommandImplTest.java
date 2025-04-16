package br.com.gabrielferreira.contratos.command.impl.usuario;

import br.com.gabrielferreira.contratos.domain.command.impl.usuario.CadastrarUsuarioCommandImpl;
import br.com.gabrielferreira.contratos.domain.command.perfil.ConsultarPerfilPorIdCommand;
import br.com.gabrielferreira.contratos.domain.mapper.CadastrarSaldoTotalUsuarioMapper;
import br.com.gabrielferreira.contratos.domain.mapper.CadastrarSaldoTotalUsuarioMapperImpl;
import br.com.gabrielferreira.contratos.domain.model.Usuario;
import br.com.gabrielferreira.contratos.domain.repository.UsuarioRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static br.com.gabrielferreira.contratos.tests.PerfilFactory.criarPerfis;
import static br.com.gabrielferreira.contratos.tests.UsuarioFactory.criarUsuario;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CadastrarUsuarioCommandImplTest {

    @InjectMocks
    private CadastrarUsuarioCommandImpl cadastrarUsuarioCommand;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private ConsultarPerfilPorIdCommand consultarPerfilPorIdCommand;

    @Spy
    private CadastrarSaldoTotalUsuarioMapper cadastrarSaldoTotalUsuarioMapper = new CadastrarSaldoTotalUsuarioMapperImpl();

    @Test
    @Order(1)
    @DisplayName("Deve cadastrar usuário")
    void deveCadastrarUsuario() {
        Usuario usuario = criarUsuario();

        when(usuarioRepository.buscarPorEmail(any()))
                .thenReturn(Optional.empty());
        when(consultarPerfilPorIdCommand.execute(1L))
                .thenReturn(criarPerfis().get(0));
        when(consultarPerfilPorIdCommand.execute(2L))
                .thenReturn(criarPerfis().get(1));

        when(usuarioRepository.save(any()))
                .thenAnswer(invocacao -> {
                    Usuario usuarioPassado = invocacao.getArgument(0, Usuario.class);
                    usuarioPassado.setId(1L);
                    return usuarioPassado;
                });

        Usuario usuarioCriado = cadastrarUsuarioCommand.execute(usuario);
        assertNotNull(usuarioCriado);
        assertEquals(1L, usuarioCriado.getId());
        verify(cadastrarSaldoTotalUsuarioMapper).createSaldoTotalUsuario(any());
    }
}
