package br.com.gabrielferreira.contratos.command.impl.telefone;

import br.com.gabrielferreira.contratos.domain.command.impl.telefone.AtualizarTelefoneCommandImpl;
import br.com.gabrielferreira.contratos.domain.command.telefone.ConsultarTelefoneExistentePorUsuarioCommand;
import br.com.gabrielferreira.contratos.domain.command.telefone.ConsultarTelefonePorIdCommand;
import br.com.gabrielferreira.contratos.domain.model.Telefone;
import br.com.gabrielferreira.contratos.domain.repository.TelefoneRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static br.com.gabrielferreira.contratos.tests.TelefoneFactory.atualizarTelefone;
import static br.com.gabrielferreira.contratos.tests.TelefoneFactory.telefoneCriado;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AtualizarTelefoneCommandImplTest {

    @InjectMocks
    private AtualizarTelefoneCommandImpl atualizarTelefoneCommand;

    @Mock
    private ConsultarTelefonePorIdCommand consultarTelefonePorIdCommand;

    @Mock
    private ConsultarTelefoneExistentePorUsuarioCommand consultarTelefoneExistentePorUsuarioCommand;

    @Mock
    private TelefoneRepository telefoneRepository;

    @Test
    @Order(1)
    @DisplayName("Deve atualizar telefone")
    void deveAtualizarTelefone() {
        Telefone telefone = atualizarTelefone();
        Long id = 1L;
        Long idUsuario = 1L;

        when(consultarTelefonePorIdCommand.execute(anyLong(), any()))
                .thenReturn(telefoneCriado());
        when(consultarTelefoneExistentePorUsuarioCommand.execute(anyLong(), any()))
                .thenReturn(false);
        when(telefoneRepository.saveAndFlush(any()))
                .thenAnswer(invocacao -> {
                    Telefone telefonePassado = invocacao.getArgument(0, Telefone.class);
                    telefonePassado.setDdd(telefone.getDdd());
                    telefonePassado.setNumero(telefone.getNumero());
                    return telefonePassado;
                });

        Telefone telefoneAtualizado = atualizarTelefoneCommand.execute(idUsuario, id, telefone);
        assertNotNull(telefoneAtualizado);
        assertEquals(telefone.getDdd(), telefoneAtualizado.getDdd());
        assertEquals(telefone.getNumero(), telefoneAtualizado.getNumero());
    }
}
