DROP DATABASE IF EXISTS sist_requerimento;

CREATE DATABASE sist_requerimento;

\c sist_requerimento ;

CREATE TABLE aluno (id serial primary key, nome text); 

CREATE TABLE requerimento (id serial primary key, descricao text, aluno_id integer references aluno(id)); 

CREATE TABLE anexo (id serial primary key, arquivo bytea, requerimento_id integer references requerimento (id));


INSERT INTO aluno (nome) VALUES ('IGOR');

INSERT INTO requerimento(descricao, aluno_id) VALUES ('ISOLAMENTO', 1);

INSERT INTO anexo (requerimento_id) VALUES(1);

