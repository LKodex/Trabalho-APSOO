package apsoo.controller;

import java.util.Date;
import java.util.List;

import javax.swing.JFrame;

import apsoo.database.GerBD;
import apsoo.model.Artigo;
import apsoo.model.ArtigoLocado;
import apsoo.model.Cliente;
import apsoo.model.Locacao;
import apsoo.model.Pagamento;

public class SisLoc {
    private GerBD db;

    public SisLoc(JFrame janela){
        this.db = GerBD.getInstance();
    }

    public Cliente buscarCliente(String cpf){
        if(cpf.replace("[^0-9]", "").length() != 11) return null;
        return db.buscarCliente(cpf.replace("[^0-9]", ""));
    }

    public boolean dataValida(Date inicio, Date fim){
        return inicio.before(fim);
    }

    public Locacao criarLocacao(Date dataInicio, Date dataFim, String endereco){
        return new Locacao(dataInicio, dataFim, endereco);
    }

    public List<Artigo> consultarArtigosDisponiveis(Date dataInicio, Date dataFim){
        return db.buscarArtigos(dataInicio, dataFim);
    }
    
    public int cadastrarArtigosLocados(List<ArtigoLocado> artigosLocados, int locacaoId){
        for (ArtigoLocado artigoLocado : artigosLocados) {
            db.inserirArtigoLocado(locacaoId, artigoLocado)
        }
    }

    public Pagamento criarPagamento(int id, String metodo, String observacao){
        return new Pagamento(id, metodo, observacao);
    }

    public int cadastrarPagamento(Pagamento pagamento, int locacaoId){

    }

    public void realizarLocacao(Locacao locacao){
    }
}
