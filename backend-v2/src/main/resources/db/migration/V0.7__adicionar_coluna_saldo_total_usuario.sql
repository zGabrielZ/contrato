alter table TB_USUARIO add column id_saldo_total char(36);
alter table TB_USUARIO modify column id_saldo_total char(36) not null;
alter table TB_USUARIO add constraint fk_usuario_saldo_total foreign key (id_saldo_total) references TB_SALDO_TOTAL(id);