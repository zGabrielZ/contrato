package br.com.gabrielferreira.contratos.command.impl.usuario;

import br.com.gabrielferreira.contratos.domain.command.impl.usuario.DeletarUsuarioPorIdCommandImpl;
import br.com.gabrielferreira.contratos.domain.command.usuario.ConsultarUsuarioPorIdCommand;
import br.com.gabrielferreira.contratos.domain.repository.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;

import static br.com.gabrielferreira.contratos.tests.UsuarioFactory.usuarioCriado;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DeletarUsuarioPorIdCommandImplTest {

    @InjectMocks
    private DeletarUsuarioPorIdCommandImpl deletarUsuarioPorIdCommand;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private TelefoneRepository telefoneRepository;

    @Mock
    private SaldoTotalUsuarioRepository saldoTotalUsuarioRepository;

    @Mock
    private HistoricoSaldoRepository historicoSaldoRepository;

    @Mock
    private SaldoRepository saldoRepository;

    @Mock
    private ContratoRepository contratoRepository;

    @Mock
    private ParcelaRepository parcelaRepository;

    @Mock
    private ConsultarUsuarioPorIdCommand consultarUsuarioPorIdCommand;

    @Test
    @DisplayName("Deve deletar usuário")
    @Order(1)
    void deveDeletarUsuario() {
        when(consultarUsuarioPorIdCommand.execute(anyLong()))
                .thenReturn(usuarioCriado());
        when(telefoneRepository.findAllByUsuarioId(anyLong()))
                .thenReturn(Collections.emptyList());
        when(historicoSaldoRepository.findAllByUsuarioId(anyLong()))
                .thenReturn(Collections.emptyList());
        when(saldoRepository.findAllByUsuarioId(anyLong()))
                .thenReturn(Collections.emptyList());
        when(contratoRepository.findAllByUsuarioId(anyLong()))
                .thenReturn(Collections.emptyList());
        when(contratoRepository.findAllByUsuarioId(anyLong()))
                .thenReturn(Collections.emptyList());
        doNothing().when(usuarioRepository).delete(any());
        doNothing().when(saldoTotalUsuarioRepository).deleteById(anyLong());

        assertDoesNotThrow(() -> deletarUsuarioPorIdCommand.execute(1L));
        verify(parcelaRepository, never()).findAllByContratoId(anyLong());
    }
}
