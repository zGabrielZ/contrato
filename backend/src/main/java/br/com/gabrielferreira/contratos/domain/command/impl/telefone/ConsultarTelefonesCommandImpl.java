package br.com.gabrielferreira.contratos.domain.command.impl.telefone;

import br.com.gabrielferreira.contratos.domain.command.telefone.ConsultarTelefonesCommand;
import br.com.gabrielferreira.contratos.domain.command.usuario.ConsultarUsuarioExistentePorIdCommand;
import br.com.gabrielferreira.contratos.domain.dao.QueryDslDAO;
import br.com.gabrielferreira.contratos.domain.dao.filter.TelefoneFilterModel;
import br.com.gabrielferreira.contratos.domain.dao.projection.TelefoneProjection;
import br.com.gabrielferreira.contratos.domain.exception.NaoEncontradoException;
import br.com.gabrielferreira.contratos.domain.mapper.ConsultarTelefoneMapper;
import br.com.gabrielferreira.contratos.domain.model.QTelefone;
import br.com.gabrielferreira.contratos.domain.model.QUsuario;
import br.com.gabrielferreira.contratos.domain.model.Telefone;
import br.com.gabrielferreira.contratos.domain.model.enums.TipoTelefoneEnum;
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
public class ConsultarTelefonesCommandImpl implements ConsultarTelefonesCommand {

    private final ConsultarUsuarioExistentePorIdCommand consultarUsuarioExistentePorIdCommand;

    private final QueryDslDAO queryDslDAO;

    private final ConsultarTelefoneMapper consultarTelefoneMapper;

    @Override
    public Page<Telefone> execute(Long idUsuario, Pageable pageable, TelefoneFilterModel filtro) {
        if (!consultarUsuarioExistentePorIdCommand.execute(idUsuario)) {
            throw new NaoEncontradoException("Usuário não encontrado");
        }

        QTelefone qTelefone = QTelefone.telefone;
        QUsuario qUsuario = QUsuario.usuario;
        BooleanBuilder query = new BooleanBuilder();
        montarQuery(query, filtro, qTelefone, idUsuario);

        List<TelefoneProjection> telefoneProjections = queryDslDAO.query(q -> q.select(Projections.constructor(
                        TelefoneProjection.class,
                        qTelefone.id,
                        qTelefone.ddd,
                        qTelefone.numero,
                        qTelefone.descricao,
                        qTelefone.tipoTelefone,
                        qTelefone.dataCadastro,
                        qTelefone.dataAtualizacao
                ))).from(qTelefone)
                .innerJoin(qTelefone.usuario, qUsuario)
                .where(query)
                .orderBy(montarOrderBy(pageable.getSort(), qTelefone))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        List<Telefone> telefones = consultarTelefoneMapper.toTelefones(telefoneProjections);
        return new PageImpl<>(telefones, pageable, telefones.size());
    }

    private OrderSpecifier<?>[] montarOrderBy(Sort sorts, QTelefone qTelefone) {
        List<OrderSpecifier<?>> orderSpecifiers = new ArrayList<>();
        if(sorts.isEmpty()){
            orderSpecifiers.add(orderByDataCadastroDesc(qTelefone));
        } else {
            orderBy(sorts, orderSpecifiers, qTelefone);
        }

        if(orderSpecifiers.isEmpty()){
            orderSpecifiers.add(orderByDataCadastroDesc(qTelefone));
        }

        return orderSpecifiers.toArray(OrderSpecifier[]::new);
    }

    private void orderBy(Sort sorts, List<OrderSpecifier<?>> orderSpecifiers, QTelefone qTelefone){
        sorts.forEach(sort -> {
            String propriedade = sort.getProperty();
            String direcao = sort.getDirection().name();

            Order order = "asc".equalsIgnoreCase(direcao)? Order.ASC : Order.DESC;

            if(propriedade.equals("id")){
                orderSpecifiers.add(new OrderSpecifier<>(order, qTelefone.id));
            }

            if(propriedade.equals("ddd")){
                orderSpecifiers.add(new OrderSpecifier<>(order, qTelefone.ddd));
            }

            if(propriedade.equals("numero")){
                orderSpecifiers.add(new OrderSpecifier<>(order, qTelefone.numero));
            }

            if(propriedade.equals("descricao")){
                orderSpecifiers.add(new OrderSpecifier<>(order, qTelefone.descricao));
            }

            if(propriedade.equals("dataCadastro")){
                orderSpecifiers.add(new OrderSpecifier<>(order, qTelefone.dataCadastro));
            }

            if(propriedade.equals("dataAtualizacao")){
                orderSpecifiers.add(new OrderSpecifier<>(order, qTelefone.dataAtualizacao));
            }

            orderByTipoTelefone(propriedade, orderSpecifiers, order, qTelefone);
        });
    }

    private void orderByTipoTelefone(String propriedade, List<OrderSpecifier<?>> orderSpecifiers, Order order, QTelefone qTelefone) {
        if(propriedade.equals("tipoTelefone")){
            orderSpecifiers.add(new OrderSpecifier<>(order, qTelefone.tipoTelefone));
        }

        if(propriedade.equals("tipoTelefoneDescricao")){
            orderSpecifiers.add(new OrderSpecifier<>(order, qTelefone.tipoTelefone));
        }
    }

    private OrderSpecifier<?> orderByDataCadastroDesc(QTelefone qTelefone){
        return qTelefone.dataCadastro.desc();
    }

    private void montarQuery(BooleanBuilder query, TelefoneFilterModel filtro, QTelefone qTelefone, Long idUsuario) {
        query.and(qTelefone.usuario.id.eq(idUsuario));

        if (filtro.isIdExistente()) {
            query.and(qTelefone.id.eq(filtro.getId()));
        }

        if (filtro.isDddExistente()) {
            query.and(qTelefone.ddd.likeIgnoreCase(Expressions.asString("%").concat(filtro.getDdd().trim()).concat("%")));
        }

        if (filtro.isNumeroExistente()) {
            query.and(qTelefone.numero.likeIgnoreCase(Expressions.asString("%").concat(filtro.getNumero().trim()).concat("%")));
        }

        if (filtro.isDescricaoExistente()) {
            query.and(qTelefone.descricao.likeIgnoreCase(Expressions.asString("%").concat(filtro.getDescricao().trim()).concat("%")));
        }

        if (filtro.isTipoTelefoneExistente()) {
            query.and(qTelefone.tipoTelefone.eq(TipoTelefoneEnum.valueOf(filtro.getTipoTelefone())));
        }

        if (filtro.isDataCadastroExistente()) {
            ZonedDateTime dataCadastroInicio = ZonedDateTime.of(filtro.getDataCadastro(), LocalTime.of(0, 0, 0), UTC);
            ZonedDateTime dataCadastroFim = ZonedDateTime.of(filtro.getDataCadastro(), LocalTime.of(23, 59, 59), UTC);

            query.and(qTelefone.dataCadastro.goe(dataCadastroInicio));
            query.and(qTelefone.dataCadastro.loe(dataCadastroFim));
        }

        if (filtro.isDataAtualizacaoExistente()) {
            ZonedDateTime dataAtualizacaoInicio = ZonedDateTime.of(filtro.getDataAtualizacao(), LocalTime.of(0, 0, 0), UTC);
            ZonedDateTime dataAtualizacaoFim = ZonedDateTime.of(filtro.getDataAtualizacao(), LocalTime.of(23, 59, 59), UTC);

            query.and(qTelefone.dataAtualizacao.goe(dataAtualizacaoInicio));
            query.and(qTelefone.dataCadastro.loe(dataAtualizacaoFim));
        }
    }
}
