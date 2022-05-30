package apsoo.database;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import apsoo.Main;
import apsoo.utils.ConfigLoader;

import java.sql.ResultSet;

public class Conexao {
    private static Conexao instance;
    private final String url;
	private final String username;
	private final String password;
    private ConfigLoader config;
    private Connection connection;
    private String databaseFolder;

    private Conexao(){
        getAndCreateDatabaseFolder();
        config=ConfigLoader.getIstance();
        
        url=String.format("jdbc:sqlite:%s/%s.db", databaseFolder.replaceAll("\\\\", "/"), config.get("DB_NAME").strip()).replaceAll("/", "\\\\");
        password=config.get("DB_PASS");
        username=config.get("DB_USER");

        try{
            connection = DriverManager.getConnection(url, username, password);
        }
        catch(SQLException e){
            System.out.println("Erro ao abrir o banco de dados sqlite");
            e.printStackTrace();
        }

        resetarBD();
    }

    public static Conexao getInstance(){
        if(instance == null) instance = new Conexao();
        return instance;
    }

    private void getAndCreateDatabaseFolder(){
        try {
            databaseFolder = URLDecoder.decode(new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI()).getParent() + "\\database", "UTF-8");
            File databaseDir = new File(databaseFolder);

            if(!databaseDir.exists()){
                databaseDir.mkdir();
            }
        } catch (UnsupportedEncodingException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public int insert(String sqlCommand){
        int result = -1;
        try{
            Statement statement = connection.createStatement();
            result = statement.executeUpdate(sqlCommand);
        }
        catch(SQLException e){
            System.out.println("Erro ao executar comando SQL de Inserção! Retornando -1");
        }
        return result;
    }

    public ResultSet select(String sqlCommand){
        ResultSet resultSet = null;
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlCommand);
        } catch (SQLException e) {
            System.out.println("Erro ao executar comando SQL de Seleção! Retornando null");
        }
        return resultSet;
    }

    public int update(String sqlCommand){
        int result = -1;
        try {
            Statement statement = connection.createStatement();
            result = statement.executeUpdate(sqlCommand);
        }
        catch(SQLException e){
            System.out.println("Erro ao executar comando SQL de Atualização! Retornando -1");
        }
        return result;
    }

    public int delete(String sqlCommand){
        int result = -1;
        try {
            Statement statement = connection.createStatement();
            result = statement.executeUpdate(sqlCommand);
        }
        catch(SQLException e){
            System.out.println("Erro ao executar comando SQL de Deleção! Retornando -1");
        }
        return result;
    }

    public void resetarBD(){
        try {
            Statement statement = connection.createStatement();

            statement.execute("DROP TABLE IF EXISTS Pagamento;");
            statement.execute("DROP TABLE IF EXISTS ArtigoLocado;");
            statement.execute("DROP TABLE IF EXISTS Locacao;");
            statement.execute("DROP TABLE IF EXISTS Cliente;");
            statement.execute("DROP TABLE IF EXISTS Funcionario;");
            statement.execute("DROP TABLE IF EXISTS Pessoa;");
            statement.execute("DROP TABLE IF EXISTS Artigo;");

            statement.execute("CREATE TABLE IF NOT EXISTS Pessoa(cpf CHARACTER(11) PRIMARY KEY, nome VARCHAR(100));");
            statement.execute("CREATE TABLE IF NOT EXISTS Cliente(cpf CHARACTER(11) PRIMARY KEY, celular CHARACTER(11), FOREIGN KEY(cpf) REFERENCES Pessoa(cpf));");
            statement.execute("CREATE TABLE IF NOT EXISTS Funcionario(cpf CHARACTER(11) PRIMARY KEY, senha CHARACTER(256) NOT NULL, FOREIGN KEY(cpf) REFERENCES Pessoa(cpf));");
            statement.execute("CREATE TABLE IF NOT EXISTS Locacao(id SERIAL PRIMARY KEY, cpfCliente CHARACTER(11) NOT NULL, cpfFuncionario CHARACTER(11) NOT NULL, inicioPrevisto DATE, fimPrevisto DATE, dataReservada DATE, endereco VARCHAR(255), FOREIGN KEY(cpfFuncionario) REFERENCES Funcionario(cpf), FOREIGN KEY(cpfCliente) REFERENCES Cliente(cpf));");
            statement.execute("CREATE TABLE IF NOT EXISTS Artigo(codigo INT PRIMARY KEY, nomeArtigo VARCHAR(100), valorDiario FLOAT(53), estoqueTotal INT);");
            statement.execute("CREATE TABLE IF NOT EXISTS ArtigoLocado(id INT, codigo INT, quantidade INT, valorCotado FLOAT(53), valorTotal FLOAT(53), FOREIGN KEY(codigo) REFERENCES Artigo(codigo), FOREIGN KEY(id) REFERENCES Locacao(id), PRIMARY KEY (id,codigo));");
            statement.execute("CREATE TABLE IF NOT EXISTS Pagamento(id VARCHAR(255), locId INT, formaPagamento VARCHAR(255), info VARCHAR(255), FOREIGN KEY (locId) REFERENCES Locacao(id), PRIMARY KEY (id, locId));");

            statement.execute("INSERT INTO Pessoa (cpf, nome) VALUES ('123','Marco');");
            statement.execute("INSERT INTO Pessoa (cpf, nome) VALUES ('987','Lucas');");
            statement.execute("INSERT INTO Pessoa (cpf, nome) VALUES ('234','Larryssa');");
            statement.execute("INSERT INTO Pessoa (cpf, nome) VALUES ('876','Kélvisck');");

            statement.execute("INSERT INTO Funcionario (cpf, senha) VALUES('123','senha123');");
            statement.execute("INSERT INTO Funcionario (cpf, senha) VALUES('234','senha234');");

            statement.execute("INSERT INTO Cliente (cpf, celular) VALUES('987','67987654321');");
            statement.execute("INSERT INTO Cliente (cpf, celular) VALUES('876','67123456789');");

            statement.execute("INSERT INTO Artigo(codigo, nomeArtigo, valorDiario, estoqueTotal) VALUES ('1','Pula Pula','250.0','25');");
            statement.execute("INSERT INTO Artigo(codigo, nomeArtigo, valorDiario, estoqueTotal) VALUES ('2','Tobogã','150.0','15');");
            statement.execute("INSERT INTO Artigo(codigo, nomeArtigo, valorDiario, estoqueTotal) VALUES ('3','Piscina de Bolinhas','750.0','45');");
            statement.execute("INSERT INTO Artigo(codigo, nomeArtigo, valorDiario, estoqueTotal) VALUES ('4','Gado Mecanico','125.0','17');");
        } catch (SQLException e){
            System.out.println("Erro ao resetar o BD");
            e.printStackTrace();
        }
    }
}
