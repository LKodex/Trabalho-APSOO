package apsoo.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
                String nome="";
                try {
                    String celular=rs.getString("celular");

                    ResultSet rs2=con.select("Select * FROM pessoa WHERE cpf='"+cpf+"'");
                    if(rs2.next()){
                        nome=rs2.getString("nome");
                    }
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
    public List<Artigo> buscarArtigos(Date inicioLocacao, Date fimLocacao){
        List<Artigo> lista=new ArrayList<Artigo>();
        ResultSet rs;
        try {
            rs=con.select("SELECT * FROM artigo");
            while(rs.next()){
                int cod=rs.getInt("codigo");
                String nome=rs.getString("nome");
                double valor=rs.getDouble("valorDiaria");
                int estoque=rs.getInt("estoqueTotal");
                Artigo throwaway=new Artigo(cod,nome,valor,estoque);
                lista.add(throwaway);
            }
            return lista;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }
    
    public String inserirLocacao(Locacao locacao){
        if(locacao.getInicio().after(locacao.getFim())){
            return "Datas invalidas";
        }
        String resultado;
        resultado=con.insert("INSERT INTO Locacao (dataReservada,inicio,fim,endereco) VALUES ('"+locacao.getDataReservada()+"','"+locacao.getInicio()+"','"+locacao.getFim()+"','"+locacao.getEndereco()+"'");
        resultado=con.insert("INSERT INTO Locacao (cpfFuncionario)VALUES('"+locacao.funcionario.getCpf+"'");
        resultado=con.insert("INSERT INTO Locacao (cpfCliente)VALUES('"+locacao.cliente.getCpf+"'");
    }
    public String atualizarQuantidade(Artigo artigo){
        String resultado;
        resultado=con.update("Update artigo SET estoqueTotal = '"+artigo.getEstoqueTotal()+"' WHERE codigo ='"+artigo.getCodigo()+"'");
        return resultado;
    }
}
