package apsoo.controller;

import java.util.Date;
import java.util.List;

import javax.swing.JFrame;

import apsoo.database.GerBD;
import apsoo.model.Artigo;
import apsoo.model.ArtigoLocado;
import apsoo.model.Cliente;
import apsoo.model.Funcionario;
import apsoo.model.Locacao;
import apsoo.model.Pagamento;

public class SisLoc {
    private GerBD db;

    public SisLoc(){
        this.db = GerBD.getInstance();
    }

    public boolean dataValida(Date inicio, Date fim){
        return inicio.before(fim);
    }

    // INSTANCIAÇÃO
    public Locacao criarLocacao(Date dataInicio, Date dataFim, String endereco){
        return new Locacao(dataInicio, dataFim, endereco);
    }

    public Pagamento criarPagamento(int id, String metodo, String observacao){
        return new Pagamento(id, metodo, observacao);
    }

    // CONSULTA
    public Cliente buscarCliente(String cpf){
        //if(cpf.replace("[^0-9]", "").length() != 11) return null;
        return db.buscarCliente(cpf.replace("[^0-9]", ""));
    }

    public Funcionario buscarFuncionario(String cpf){
        //if(cpf.replace("[^0-9]", "").length() != 11) return null;
        return db.buscarFuncionario(cpf.replace("[^0-9]", ""));
    }

    public List<Artigo> consultarArtigosDisponiveis(){
        return db.buscarArtigos();
    }

    // PERSISTENCIA
    public void cadastrarArtigosLocados(List<ArtigoLocado> artigosLocados, int locacaoId){
        for (ArtigoLocado artigoLocado : artigosLocados) {
            db.inserirArtigoLocado(locacaoId, artigoLocado);
        }
    }

    public boolean cadastrarPagamento(Pagamento pagamento, int locacaoId){
        return db.inserirPagamento(locacaoId, pagamento);
    }

    public int cadastrarLocacao(Locacao locacao){
        return db.inserirLocacao(locacao);
    }
}
