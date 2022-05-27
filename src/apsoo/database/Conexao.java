package apsoo.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import apsoo.utils.ConfigLoader;

import java.sql.ResultSet;

public class Conexao {
    private static Conexao instance;
    private final String url;
	private final String username;
	private final String password;
    private ConfigLoader config;

    private Conexao(){
        config=ConfigLoader.getIstance();
        url=String.format("jdbc:postgresql://%s:%s/%s", config.get("DB_HOST"),config.get("DB_PORT"),config.get("DB_NAME"));
        password=config.get("DB_PASS");
        username=config.get("DB_USER");
    }

    public static Conexao getInstance(){
        if(instance == null) instance = new Conexao();
        return instance;
    }

    public int insert(String sqlCommand){
        int result = -1;
        try{
            Connection connection = DriverManager.getConnection(url, username, password);
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
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(sqlCommand);
        } catch (SQLException e) {
            System.out.println("Erro ao executar comando SQL de Seleção! Retornando null");
        }
        return resultSet;
    }

    public int update(String sqlCommand){
        int result = -1;
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
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
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            Statement statement = connection.createStatement();
            result = statement.executeUpdate(sqlCommand);
        }
        catch(SQLException e){
            System.out.println("Erro ao executar comando SQL de Deleção! Retornando -1");
        }
        return result;
    }
}
