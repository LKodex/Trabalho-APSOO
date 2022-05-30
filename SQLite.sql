
DROP TABLE IF EXISTS Pagamento;
DROP TABLE IF EXISTS ArtigoLocado;
DROP TABLE IF EXISTS Locacao;
DROP TABLE IF EXISTS Cliente;
DROP TABLE IF EXISTS Funcionario;
DROP TABLE IF EXISTS Pessoa;
DROP TABLE IF EXISTS Artigo;

CREATE TABLE IF NOT EXISTS Pessoa(
	cpf CHARACTER(11) PRIMARY KEY,
	nome VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS Cliente(
	cpf CHARACTER(11) PRIMARY KEY,
	celular CHARACTER(11),
	FOREIGN KEY(cpf) REFERENCES Pessoa(cpf)
);

CREATE TABLE IF NOT EXISTS Funcionario(
	cpf CHARACTER(11) PRIMARY KEY,
	senha CHARACTER(256) NOT NULL,
	FOREIGN KEY(cpf) REFERENCES Pessoa(cpf)
);

CREATE TABLE IF NOT EXISTS Locacao(
	id SERIAL PRIMARY KEY,
	cpfCliente CHARACTER(11) NOT NULL,
	cpfFuncionario CHARACTER(11) NOT NULL,
	inicioPrevisto DATE,
	fimPrevisto DATE,
	dataReservada DATE,
	endereco VARCHAR(255),
	FOREIGN KEY(cpfFuncionario) REFERENCES Funcionario(cpf),
	FOREIGN KEY(cpfCliente) REFERENCES Cliente(cpf)
);

CREATE TABLE IF NOT EXISTS Artigo(
	codigo INT PRIMARY KEY,
	nomeArtigo VARCHAR(100),
	valorDiario FLOAT(53),
	estoqueTotal INT
);

CREATE TABLE IF NOT EXISTS ArtigoLocado(
	id INT,
	codigo INT,
	quantidade INT,
	valorCotado FLOAT(53),
	valorTotal FLOAT(53),
	FOREIGN KEY(codigo) REFERENCES Artigo(codigo),
	FOREIGN KEY(id) REFERENCES Locacao(id),
	PRIMARY KEY (id,codigo)
);

CREATE TABLE IF NOT EXISTS Pagamento(
	id VARCHAR(255),
	locId INT,
	formaPagamento VARCHAR(255),
	info VARCHAR(255),
	FOREIGN KEY (locId) REFERENCES Locacao(id),
	PRIMARY KEY (id, locId)
);

INSERT INTO Pessoa (cpf, nome) VALUES ('123','Marco');
INSERT INTO Pessoa (cpf, nome) VALUES ('987','Lucas');
INSERT INTO Pessoa (cpf, nome) VALUES ('234','Larryssa');
INSERT INTO Pessoa (cpf, nome) VALUES ('876','Kélvisck');

INSERT INTO Funcionario (cpf, senha) VALUES('123','senha123');
INSERT INTO Funcionario (cpf, senha) VALUES('234','senha234');

INSERT INTO Cliente (cpf, celular) VALUES('987','67987654321');
INSERT INTO Cliente (cpf, celular) VALUES('876','67123456789');

INSERT INTO Artigo(codigo, nomeArtigo, valorDiario, estoqueTotal) VALUES ('1','Pula Pula','250.0','25');
INSERT INTO Artigo(codigo, nomeArtigo, valorDiario, estoqueTotal) VALUES ('2','Tobogã','150.0','15');
INSERT INTO Artigo(codigo, nomeArtigo, valorDiario, estoqueTotal) VALUES ('3','Piscina de Bolinhas','750.0','45');
INSERT INTO Artigo(codigo, nomeArtigo, valorDiario, estoqueTotal) VALUES ('4','Gado Mecanico','125.0','17');
