DROP DATABASE IF EXISTS db_dev_perguntar;
CREATE DATABASE db_dev_perguntar;
USE db_dev_perguntar;

CREATE TABLE usuario
(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    e_mail VARCHAR(255) UNIQUE NOT NULL,
    senha VARCHAR(50) NOT NULL
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
);

CREATE TABLE resposta
(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    conteudo TEXT NOT NULL,
    id_pergunta INT NOT NULL,
    FOREIGN KEY(id_pergunta) REFERENCES pergunta(id)
    ON DELETE CASCADE,
    id_usuario INT NULL,
    FOREIGN KEY(id_usuario) REFERENCES usuario(id),
    id_resposta INT NULL,
    FOREIGN KEY(id_resposta) REFERENCES resposta(id)
);