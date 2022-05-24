CREATE TABLE IF NOT EXISTS Pessoa(
	cpf char(11) PRIMARY KEY,
	nome varchar(100)
);
CREATE TABLE IF NOT EXISTS Cliente(
	celular char(11),
	cpf char(11) NOT NULL,
	FOREIGN KEY(cpf) REFERENCES Pessoa(cpf)
);
CREATE TABLE IF NOT EXISTS Funcionario(
	senha char(256),
	cpf char(11) NOT NULL,
	FOREIGN KEY(cpf) REFERENCES Pessoa(cpf)
);
CREATE TABLE IF NOT EXISTS Locacao(
	id int PRIMARY KEY,
	cpf char(11) NOT NULL,
	inicioPrevisto date,
	fimPrevisto date,
	dataReservada date,
	endereco varchar(255),
	FOREIGN KEY(cpf) REFERENCES Funcionario(cpf),
	FOREIGN KEY(cpf) REFERENCES Cliente(cpf)
);
CREATE TABLE IF NOT EXISTS Artigos(
	codigo int PRIMARY KEY,
	nomeArtigo varchar(100),
	valorDiario float(53),
	estoqueTotal int
);
CREATE TABLE IF NOT EXISTS ArtigoLocado(
	id int,
	codigo int,
	quantidade int,
	valorCotado float(53),
	valorTotal float(53),
	FOREIGN KEY (codigo) REFERENCES Artigos (codigo),
	FOREIGN KEY(id) REFERENCES Locacao(id)
);