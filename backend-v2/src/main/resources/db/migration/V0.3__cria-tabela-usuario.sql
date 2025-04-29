create table TB_USUARIO(
    id char(36) not null default (uuid()),
    nome varchar(255) not null,
    sobrenome varchar(255) not null,
    email varchar(255) not null,
    senha varchar(255) not null,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP not null,
    data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    primary key (id)
);

alter table TB_USUARIO add constraint uk_usuario_email unique (email);