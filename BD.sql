DROP TABLE IF EXISTS Pessoa CASCADE;
DROP TABLE IF EXISTS Cliente CASCADE;
DROP TABLE IF EXISTS Funcionario CASCADE;
DROP TABLE IF EXISTS Pessoa CASCADE;
DROP TABLE IF EXISTS Locacao CASCADE;
DROP TABLE IF EXISTS Artigos CASCADE;
DROP TABLE IF EXISTS ArtigoLocado CASCADE;

CREATE TABLE Pessoa(
	cpf char(11) PRIMARY KEY,
	nome varchar(100)
);
CREATE TABLE Cliente(
	celular char(11),
	FOREIGN KEY(cpf) REFERENCES Pessoa(cpf)
);
CREATE TABLE Funcionario(
	senha char(256),
	FOREIGN KEY(cpf) REFERENCES Pessoa(cpf)
);
CREATE TABLE Locacao(
	id int PRIMARY KEY,
	inicioPrevisto date,
	fimPrevisto date,
	dataReservada date,
	enddereco varchar(255)
);
CREATE TABLE Artigos(
	codigo int PRIMARY KEY,
	nomeArtigo varchar(100),
	valorDiario float(53),
	estoqueTotal int
);
CREATE TABLE ArtigoLocado(
	id int,
	quantidade int,
	valorCotado float(53),
	valorTotal float(53)
);
