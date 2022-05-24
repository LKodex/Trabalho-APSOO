CREATE TABLE IF NOT EXISTS Pessoa(
	cpf char(11) PRIMARY KEY,
	nome varchar(100),
	FOREIGN KEY(cpf) REFERENCES Cliente(cpf),
	FOREIGN KEY(cpf) REFERENCES Funcionario(cpf)
);
CREATE TABLE IF NOT EXISTS Cliente(
	celular char(11),
	cpf char(11) PRIMARY KEY
);
CREATE TABLE IF NOT EXISTS Funcionario(
	senha char(256),
	cpf char(11) PRIMARY KEY
);
CREATE TABLE IF NOT EXISTS Locacao(
	id int PRIMARY KEY,
	cpf char(11) NOT NULL,
	inicioPrevisto date,
	fimPrevisto date,
	dataReservada date,
	endereco varchar(255),
	FOREIGN KEY(cpf) REFERENCES Funcionario(cpf),
	FOREIGN KEY(cpf) REFERENCES Cliente(cpf),
	FOREIGN KEY(id) REFERENCES ArtigoLocado(id)
);
CREATE TABLE IF NOT EXISTS Artigos(
	codigo int PRIMARY KEY,
	nomeArtigo varchar(100),
	valorDiario float(53),
	estoqueTotal int
);
CREATE TABLE IF NOT EXISTS ArtigoLocado(
	id int PRIMARY KEY,
	codigo int PRIMARY KEY,
	quantidade int,
	valorCotado float(53),
	valorTotal float(53),
	FOREIGN KEY (codigo) REFERENCES Artigos (codigo),
	FOREIGN KEY(id) REFERENCES Locacao(id)
);