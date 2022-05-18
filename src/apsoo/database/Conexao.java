package apsoo.database;

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
        // TODO
    }

    public String update(String sqlCommand){
        // TODO
    }

    public String delete(String sqlCommand){
        // TODO
    }
}
