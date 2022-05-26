package apsoo.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import apsoo.model.Artigo;
import apsoo.model.Cliente;
import apsoo.model.Locacao;

public class GerBD {
    private static GerBD instance;
    Conexao con;
    private GerBD(){
        con=Conexao.getInstance();
    }

    public static GerBD getInstance(){
        if(instance == null) instance = new GerBD();
        return instance;
    }

    public Cliente buscarCliente(String cpf){
        ResultSet rs;
        rs=con.select("SELECT * FROM Cliente WHERE cpf ='"+cpf+"'");
        Cliente resultado = null;
        try {
            if(rs.next()){
                String nome;
                try {
                    nome = rs.getString("nome");
                    String celular=rs.getString("celular");
                    resultado=new Cliente(nome,cpf,celular);
                    return resultado;
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resultado;
    }
/*
    public List<Artigo> buscarArtigos(Date inicioLocacao, Date fimLocacao){
        // TODO
    }

    public String inserirLocacao(Locacao locacao){
        // TODO
    }

    public String atualizarQuantidade(Artigo artigo){
        // TODO
    }*/
}
