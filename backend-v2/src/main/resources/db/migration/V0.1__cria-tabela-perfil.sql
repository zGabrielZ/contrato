create table TB_PERFIL(
    id char(36) not null default (uuid()),
    descricao varchar(255) not null,
    autoriedade varchar(255) not null,
    primary key (id)
);

alter table TB_PERFIL add constraint uk_perfil_autoriedade unique (autoriedade);