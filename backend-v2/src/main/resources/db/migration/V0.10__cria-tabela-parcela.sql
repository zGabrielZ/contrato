create table TB_PARCELA(
    id char(36) not null default (uuid()),
    data TIMESTAMP not null,
    valor decimal(10, 2) not null,
    situacao_parcela varchar(255) not null,
    id_contrato char(36) not null,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP not null,
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key (id)
);

alter table TB_PARCELA add constraint fk_parcela_contrato foreign key (id_contrato) references TB_CONTRATO(id);
alter table TB_PARCELA add constraint ck_situacao_parcela_01 check (situacao_parcela in ('EM_ANDAMENTO','PAGO', 'ATRASAOD'));