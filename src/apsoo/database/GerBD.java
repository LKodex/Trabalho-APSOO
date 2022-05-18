package apsoo.database;

import java.util.Date;
import java.util.List;

import apsoo.model.Artigo;
import apsoo.model.Cliente;
import apsoo.model.Locacao;

public class GerBD {
    private static GerBD instance;

    private GerBD(){

    }

    public static GerBD getInstance(){
        if(instance == null) instance = new GerBD();
        return instance;
    }

    public Cliente buscarCliente(String cpf){
        // TODO
    }

    public List<Artigo> buscarArtigos(Date inicioLocacao, Date fimLocacao){
        // TODO
    }

    public String inserirLocacao(Locacao locacao){
        // TODO
    }

    public String atualizarQuantidade(Artigo artigo){
        // TODO
    }
}
