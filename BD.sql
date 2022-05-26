CREATE TABLE IF NOT EXISTS Pessoa(
	cpf char(11) PRIMARY KEY,
	nome varchar(100),
);
CREATE TABLE IF NOT EXISTS Cliente(
	cpf char(11) PRIMARY KEY,
	celular char(11),
	FOREIGN KEY(cpf) REFERENCES Pessoa(cpf)
);
CREATE TABLE IF NOT EXISTS Funcionario(
	cpf char(11) PRIMARY KEY,
	senha char(256) NOT NULL,
	FOREIGN KEY(cpf) REFERENCES Pessoa(cpf)
);
CREATE TABLE IF NOT EXISTS Locacao(
	id int PRIMARY KEY,
	cpfCliente char(11) NOT NULL,
	cpfFuncionario char(11) NOT NULL,
	inicioPrevisto date,
	fimPrevisto date,
	dataReservada date,
	endereco varchar(255),
	FOREIGN KEY(cpfFuncionario) REFERENCES Funcionario(cpf),
	FOREIGN KEY(cpfCliente) REFERENCES Cliente(cpf)
);
CREATE TABLE IF NOT EXISTS Artigo(
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
	FOREIGN KEY(codigo) REFERENCES Artigo(codigo),
	FOREIGN KEY(id) REFERENCES Locacao(id),
	PRIMARY KEY (id,codigo)
);