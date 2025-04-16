package br.com.gabrielferreira.contratos.domain.command.impl.usuario;

import br.com.gabrielferreira.contratos.domain.command.usuario.ConsultarUsuariosCommand;
import br.com.gabrielferreira.contratos.domain.dao.QueryDslDAO;
import br.com.gabrielferreira.contratos.domain.dao.projection.UsuarioProjection;
import br.com.gabrielferreira.contratos.domain.exception.RegraDeNegocioException;
import br.com.gabrielferreira.contratos.domain.mapper.ConsultarUsuarioMapper;
import br.com.gabrielferreira.contratos.domain.model.QSaldoTotalUsuario;
import br.com.gabrielferreira.contratos.domain.model.QUsuario;
import br.com.gabrielferreira.contratos.domain.model.Usuario;
import br.com.gabrielferreira.contratos.domain.dao.filter.UsuarioFilterModel;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import static br.com.gabrielferreira.contratos.common.utils.DataUtils.UTC;

@Component
@RequiredArgsConstructor
public class ConsultarUsuariosCommandImpl implements ConsultarUsuariosCommand {

    private final QueryDslDAO queryDslDAO;

    private final ConsultarUsuarioMapper consultarUsuarioMapper;

    @Override
    public Page<Usuario> execute(Pageable pageable, UsuarioFilterModel filtro) {
        validarFiltros(filtro);

        QUsuario qUsuario = QUsuario.usuario;
        QSaldoTotalUsuario qSaldoTotalUsuario = QSaldoTotalUsuario.saldoTotalUsuario;
        BooleanBuilder query = new BooleanBuilder();
        montarQuery(query, filtro, qUsuario);

        List<UsuarioProjection> usuarioProjections = queryDslDAO.query(q -> q.select(Projections.constructor(
                        UsuarioProjection.class,
                        qUsuario.id,
                        qUsuario.nome,
                        qUsuario.sobrenome,
                        qUsuario.email,
                        qSaldoTotalUsuario.valor,
                        qUsuario.dataCadastro,
                        qUsuario.dataAtualizacao
                ))).from(qUsuario)
                .innerJoin(qUsuario.saldoTotal, qSaldoTotalUsuario)
                .where(query)
                .orderBy(montarOrderBy(pageable.getSort(), qUsuario))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        List<Usuario> usuarios = consultarUsuarioMapper.toUsuarios(usuarioProjections);
        return new PageImpl<>(usuarios, pageable, usuarios.size());
    }

    private void validarFiltros(UsuarioFilterModel filtro) {
        if (filtro.isSaldoTotalInicialExistente() && !filtro.isSaldoTotalFinalExistente()) {
            throw new RegraDeNegocioException("É necessário informar o saldo total final");
        }

        if (!filtro.isSaldoTotalInicialExistente() && filtro.isSaldoTotalFinalExistente()) {
            throw new RegraDeNegocioException("É necessário informar o saldo total inicial");
        }

        if (filtro.isSaldoTotalInicialExistente() && filtro.isSaldoTotalFinalExistente()
                && filtro.getSaldoTotalInicial().compareTo(filtro.getSaldoTotalFinal()) > 0) {
            throw new RegraDeNegocioException("Saldo total inicial é maior que o saldo total final");
        }
    }

    private void montarQuery(BooleanBuilder query, UsuarioFilterModel filtro, QUsuario qUsuario) {
        if (filtro.isIdExistente()) {
            query.and(qUsuario.id.eq(filtro.getId()));
        }

        if (filtro.isNomeExistente()) {
            query.and(qUsuario.nome.likeIgnoreCase(Expressions.asString("%").concat(filtro.getNome().trim()).concat("%")));
        }

        if (filtro.isSobrenomeExistente()) {
            query.and(qUsuario.sobrenome.likeIgnoreCase(Expressions.asString("%").concat(filtro.getSobrenome().trim()).concat("%")));
        }

        if (filtro.isEmailExistente()) {
            query.and(qUsuario.email.likeIgnoreCase(Expressions.asString("%").concat(filtro.getEmail().trim()).concat("%")));
        }

        if (filtro.isSaldoTotalInicialExistente()) {
            query.and(qUsuario.saldoTotal.valor.goe(filtro.getSaldoTotalInicial()));
        }

        if (filtro.isSaldoTotalFinalExistente()) {
            query.and(qUsuario.saldoTotal.valor.loe(filtro.getSaldoTotalFinal()));
        }

        if (filtro.isDataCadastroExistente()) {
            ZonedDateTime dataCadastroInicio = ZonedDateTime.of(filtro.getDataCadastro(), LocalTime.of(0, 0, 0), UTC);
            ZonedDateTime dataCadastroFim = ZonedDateTime.of(filtro.getDataCadastro(), LocalTime.of(23, 59, 59), UTC);

            query.and(qUsuario.dataCadastro.goe(dataCadastroInicio));
            query.and(qUsuario.dataCadastro.loe(dataCadastroFim));
        }

        if (filtro.isDataAtualizacaoExistente()) {
            ZonedDateTime dataAtualizacaoInicio = ZonedDateTime.of(filtro.getDataAtualizacao(), LocalTime.of(0, 0, 0), UTC);
            ZonedDateTime dataAtualizacaoFim = ZonedDateTime.of(filtro.getDataAtualizacao(), LocalTime.of(23, 59, 59), UTC);

            query.and(qUsuario.dataAtualizacao.goe(dataAtualizacaoInicio));
            query.and(qUsuario.dataCadastro.loe(dataAtualizacaoFim));
        }
    }

    private OrderSpecifier<?>[] montarOrderBy(Sort sorts, QUsuario qUsuario){
        List<OrderSpecifier<?>> orderSpecifiers = new ArrayList<>();
        if(sorts.isEmpty()){
            orderSpecifiers.add(orderByDataCadastroDesc(qUsuario));
        } else {
            orderBy(sorts, orderSpecifiers, qUsuario);
        }

        if(orderSpecifiers.isEmpty()){
            orderSpecifiers.add(orderByDataCadastroDesc(qUsuario));
        }

        return orderSpecifiers.toArray(OrderSpecifier[]::new);
    }

    private void orderBy(Sort sorts, List<OrderSpecifier<?>> orderSpecifiers, QUsuario qUsuario){
        sorts.forEach(sort -> {
            String propriedade = sort.getProperty();
            String direcao = sort.getDirection().name();

            Order order = "asc".equalsIgnoreCase(direcao)? Order.ASC : Order.DESC;

            if(propriedade.equals("id")){
                orderSpecifiers.add(new OrderSpecifier<>(order, qUsuario.id));
            }

            if(propriedade.equals("nome")){
                orderSpecifiers.add(new OrderSpecifier<>(order, qUsuario.nome));
            }

            if(propriedade.equals("sobrenome")){
                orderSpecifiers.add(new OrderSpecifier<>(order, qUsuario.sobrenome));
            }

            if(propriedade.equals("email")){
                orderSpecifiers.add(new OrderSpecifier<>(order, qUsuario.email));
            }

            if(propriedade.equals("dataCadastro")){
                orderSpecifiers.add(new OrderSpecifier<>(order, qUsuario.dataCadastro));
            }

            if(propriedade.equals("dataAtualizacao")){
                orderSpecifiers.add(new OrderSpecifier<>(order, qUsuario.dataAtualizacao));
            }

            orderBySaldoTotal(order, propriedade, orderSpecifiers, qUsuario.saldoTotal);
        });
    }

    private void orderBySaldoTotal(Order order, String propriedade, List<OrderSpecifier<?>> orderSpecifiers, QSaldoTotalUsuario qSaldoTotalUsuario) {
        if(propriedade.equals("saldoTotal")){
            orderSpecifiers.add(new OrderSpecifier<>(order, qSaldoTotalUsuario.valor));
        }
    }

    private OrderSpecifier<?> orderByDataCadastroDesc(QUsuario qUsuario){
        return qUsuario.dataCadastro.desc();
    }
}
