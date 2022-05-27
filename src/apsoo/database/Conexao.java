package apsoo.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

    public String insert(String sqlCommand){
        try{
            Connection con=DriverManager.getConnection(url, username, password);
            Statement st=con.createStatement();
            st.executeQuery(sqlCommand);
            return "Inserção bem sucedida";
        }
        catch(SQLException e){
            return "Houve um erro ao inserir";
        }
    }

    public ResultSet select(String sqlCommand){
        try {
            Connection con=DriverManager.getConnection(url, username, password);
            PreparedStatement p=con.prepareStatement(sqlCommand);
            ResultSet rs=p.executeQuery();
            con.close();
            return rs;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
    public String update(String sqlCommand){
        try{
            Connection con=DriverManager.getConnection(url, username, password);
            Statement st=con.createStatement();
            st.executeQuery(sqlCommand);
            return "O item foi atualizado com sucesso";
        }
        catch(SQLException e){
            return "Houve um erro ao atualizar";
        }
    }

    public String delete(String sqlCommand){
        try{
            Connection con=DriverManager.getConnection(url, username, password);
            Statement st=con.createStatement();
            st.executeQuery(sqlCommand);
            return "O item foi apagado com sucesso";
        }
        catch(SQLException e){
            return "Houve um erro ao apagar o item";
        }
    }
}
