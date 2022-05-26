package apsoo.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Conexao {
    private static Conexao instance;

    private Conexao(){

    }

    public static Conexao getInstance(){
        if(instance == null) instance = new Conexao();
        return instance;
    }

    public String insert(String sqlCommand){
        // TODO
    }

    public ResultSet select(String sqlCommand){
        PreparedStatement p;
        ResultSet rs;
        p = connection.prepareStatement(sqlCommand);
        rs=p.executeQuery();
        return rs;
    }

    public String update(String sqlCommand){
        // TODO
    }

    public String delete(String sqlCommand){
        // TODO
    }
}
