DROP DATABASE IF EXISTS db_dev_perguntar;
CREATE DATABASE db_dev_perguntar;
USE db_dev_perguntar;

SET SQL_SAFE_UPDATES = 0;

CREATE TABLE usuario
(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    e_mail VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(50) NOT NULL,
    ativo TINYINT not null default 1
);

CREATE TABLE categoria
(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE pergunta
(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(50) NOT NULL,
    conteudo TEXT NOT NULL,
    data_pergunta DATETIME NOT NULL,
    data_resolucao DATETIME NULL,
	id_usuario INT NULL,
    FOREIGN KEY(id_usuario) REFERENCES usuario(id),
    id_categoria INT NULL,
    FOREIGN KEY(id_categoria) REFERENCES categoria(id)
    ON DELETE SET NULL
);

CREATE TABLE resposta
(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    conteudo TEXT NOT NULL,
    solucao TINYINT NOT NULL DEFAULT 0,
    id_pergunta INT NOT NULL,
    FOREIGN KEY(id_pergunta) REFERENCES pergunta(id)
    ON DELETE CASCADE,
    id_usuario INT NULL,
    FOREIGN KEY(id_usuario) REFERENCES usuario(id),
    id_resposta INT NULL,
    FOREIGN KEY(id_resposta) REFERENCES resposta(id)
);

insert into categoria(nome) values('java');
insert into categoria(nome) values('delphi');
insert into categoria(nome) values('html');
insert into categoria(nome) values('JavaScript');
insert into categoria(nome) values('css');
insert into categoria(nome) values('java swing');
insert into categoria(nome) values('python');
insert into categoria(nome) values('sql');
insert into categoria(nome) values('mongodb');
insert into categoria(nome) values('c/c++');
insert into categoria(nome) values('linux');
insert into categoria(nome) values('hacking');

select * from resposta;

select * from categoria;

select * from pergunta;

select * from usuario;

select * from resposta;

insert into usuario(nome, e_mail, senha) values('gabriel', 'gabriel@hotmail.com', '123456');

insert into pergunta(titulo, conteudo, data_pergunta, id_usuario, id_categoria) values('pergunta1', 'pergunta1', now(), 1, 1);

insert into resposta(conteudo, id_pergunta, id_usuario) values('resposta1', 1, 1);

insert into resposta(conteudo, id_pergunta, id_usuario, id_resposta) values('resposta2', 1, 1, 1);

select * from resposta;

update usuario set ativo = 1 where id = 1;