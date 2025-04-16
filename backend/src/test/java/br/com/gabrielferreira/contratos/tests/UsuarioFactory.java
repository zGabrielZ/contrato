package br.com.gabrielferreira.contratos.tests;

import br.com.gabrielferreira.contratos.api.dto.request.AtualizarUsuarioDTO;
import br.com.gabrielferreira.contratos.api.dto.request.CriarUsuarioDTO;
import br.com.gabrielferreira.contratos.api.dto.request.PerfilIdDTO;
import br.com.gabrielferreira.contratos.domain.dao.filter.UsuarioFilterModel;
import br.com.gabrielferreira.contratos.domain.dao.projection.UsuarioProjection;
import br.com.gabrielferreira.contratos.domain.model.SaldoTotalUsuario;
import br.com.gabrielferreira.contratos.domain.model.Usuario;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static br.com.gabrielferreira.contratos.common.utils.DataUtils.UTC;
import static br.com.gabrielferreira.contratos.tests.PerfilFactory.criarPerfis;
import static br.com.gabrielferreira.contratos.tests.PerfilFactory.criarPerfisIds;

public class UsuarioFactory {

    private UsuarioFactory() {
    }

    public static Usuario criarUsuario() {
        return Usuario.builder()
                .nome("Teste usuário nome")
                .sobrenome("Teste usuário sobrenome")
                .email("Teste usuário email")
                .perfis(criarPerfis())
                .build();
    }

    public static Usuario usuarioCriado() {
        Usuario usuario = criarUsuario();
        usuario.setId(1L);
        usuario.setDataCadastro(ZonedDateTime.now(UTC));
        usuario.setDataAtualizacao(ZonedDateTime.now(UTC));
        usuario.setSaldoTotal(SaldoTotalUsuario.builder().valor(BigDecimal.ZERO).build());
        return usuario;
    }

    public static Usuario atualizarUsuario() {
        Usuario usuario = usuarioCriado();
        usuario.setNome("Teste usuário editado");
        usuario.setSobrenome("Teste usuário sobrenome editado");
        return usuario;
    }

    public static UsuarioFilterModel criarFiltroUsuario() {
        return UsuarioFilterModel.builder()
                .id(1L)
                .nome("Teste nome")
                .sobrenome("Teste sobrenome")
                .email("Teste email")
                .dataCadastro(LocalDate.now(UTC))
                .dataAtualizacao(LocalDate.now(UTC))
                .build();
    }

    public static List<UsuarioProjection> criarUsuariosProjections() {
        UsuarioProjection build = UsuarioProjection.builder()
                .id(1L)
                .saldoTotal(BigDecimal.ONE)
                .nome("nome")
                .sobrenome("sobrenome")
                .build();
        List<UsuarioProjection> usuarioProjections = new ArrayList<>();
        usuarioProjections.add(build);
        return usuarioProjections;
    }

    public static CriarUsuarioDTO criarUsuarioDto() {
        return new CriarUsuarioDTO("Teste usuário nome", "Teste usuário sobrenome", "teste@email.com", criarPerfisIds());
    }

    public static CriarUsuarioDTO criarUsuarioDtoNulo() {
        return new CriarUsuarioDTO(null, null, null, null);
    }

    public static CriarUsuarioDTO criarUsuarioDtoEmailDuplicado(String email) {
        CriarUsuarioDTO criarUsuarioDTO = criarUsuarioDto();
        return new CriarUsuarioDTO(criarUsuarioDTO.nome(), criarUsuarioDTO.sobrenome(), email, criarUsuarioDTO.perfis());
    }

    public static CriarUsuarioDTO criarUsuarioDtoPerfisDuplicados(List<PerfilIdDTO> perfis) {
        CriarUsuarioDTO criarUsuarioDTO = criarUsuarioDto();
        return new CriarUsuarioDTO(criarUsuarioDTO.nome(), criarUsuarioDTO.sobrenome(), criarUsuarioDTO.email(), perfis);
    }

    public static AtualizarUsuarioDTO criarUsuarioUpdateDto() {
        return new AtualizarUsuarioDTO("Teste usuário nome editado", "Teste usuário sobrenome editado");
    }
}
