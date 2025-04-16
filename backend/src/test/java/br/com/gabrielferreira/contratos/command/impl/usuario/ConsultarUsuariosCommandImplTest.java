package br.com.gabrielferreira.contratos.command.impl.usuario;

import br.com.gabrielferreira.contratos.domain.command.impl.usuario.ConsultarUsuariosCommandImpl;
import br.com.gabrielferreira.contratos.domain.dao.QueryDslDAO;
import br.com.gabrielferreira.contratos.domain.dao.filter.UsuarioFilterModel;
import br.com.gabrielferreira.contratos.domain.dao.projection.UsuarioProjection;
import br.com.gabrielferreira.contratos.domain.mapper.ConsultarUsuarioMapper;
import br.com.gabrielferreira.contratos.domain.mapper.ConsultarUsuarioMapperImpl;
import br.com.gabrielferreira.contratos.domain.model.QUsuario;
import br.com.gabrielferreira.contratos.domain.model.Usuario;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQuery;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static br.com.gabrielferreira.contratos.tests.UsuarioFactory.criarFiltroUsuario;
import static br.com.gabrielferreira.contratos.tests.UsuarioFactory.criarUsuariosProjections;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ConsultarUsuariosCommandImplTest {

    @InjectMocks
    private ConsultarUsuariosCommandImpl consultarUsuariosCommand;

    @Mock
    private QueryDslDAO queryDslDAO;

    @Spy
    private ConsultarUsuarioMapper consultarUsuarioMapper = new ConsultarUsuarioMapperImpl();

    @Test
    @Order(1)
    @DisplayName("Deve consultar usuário")
    void deveConsultarUsuario() {
        Pageable pageable = PageRequest.of(0, 1);
        UsuarioFilterModel filtro = criarFiltroUsuario();

        JPAQuery<UsuarioProjection> jpaQueryMock = mock(JPAQuery.class);
        when(queryDslDAO.query(q -> q.select(any(Expression.class))))
                .thenReturn(jpaQueryMock);
        when(queryDslDAO.query(any()))
                .thenReturn(jpaQueryMock);
        when(jpaQueryMock.from(any(QUsuario.class))).thenReturn(jpaQueryMock);
        when(jpaQueryMock.innerJoin(any(EntityPath.class), any())).thenReturn(jpaQueryMock);
        when(jpaQueryMock.where(any(BooleanBuilder.class))).thenReturn(jpaQueryMock);
        when(jpaQueryMock.orderBy(any(OrderSpecifier[].class))).thenReturn(jpaQueryMock);
        when(jpaQueryMock.offset(anyLong())).thenReturn(jpaQueryMock);
        when(jpaQueryMock.limit(anyLong())).thenReturn(jpaQueryMock);
        when(jpaQueryMock.fetch()).thenReturn(criarUsuariosProjections());

        Page<Usuario> usuarios = consultarUsuariosCommand.execute(pageable, filtro);
        assertFalse(usuarios.isEmpty());
        verify(consultarUsuarioMapper).toUsuario(any());
    }
}
