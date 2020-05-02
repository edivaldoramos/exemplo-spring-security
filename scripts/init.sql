create database cadastro;

\c cadastro

create table if not exists empresa
(
    id bigserial not null constraint empresa_pk primary key,
    razao_social varchar   not null,
    nome_fantasia varchar  not null,
    cnpj_raiz varchar  not null
);

create table if not exists filial
(
    id bigserial not null constraint empresa_pk primary key,
    razao_social varchar   not null,
    nome_fantasia varchar  not null,
    cnpj varchar  not null,
    id_empresa bigint add constraint empresa_filial_fk references empresa
);



