package apsoo.controller;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import apsoo.database.GerBD;
import apsoo.model.Artigo;
import apsoo.model.ArtigoLocado;
import apsoo.model.Cliente;
import apsoo.model.Funcionario;
import apsoo.model.Locacao;
import apsoo.model.Pagamento;
import apsoo.view.Janela;

public class SisLoc {
    private GerBD db;
    private Locacao locacao;
    private Janela janela;
    private SimpleDateFormat dateFormatter;

    public SisLoc(Janela janela){
        this.janela = janela;
        this.db = GerBD.getInstance();
        dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
    }

    // Passo 1
    public boolean buscarCliente(){
        String cpfCliente = janela.getClienteCpf().replaceAll("[^0-9]", "");
        String cpfFuncionario = janela.getFuncionarioCpf().replaceAll("[^0-9]", "");

        Cliente cliente = db.buscarCliente(cpfCliente);
        Funcionario funcionario = db.buscarFuncionario(cpfFuncionario);

        if (cliente == null){
            janela.mostrarMensagem("Cliente não encontrado!");
        } else if (funcionario == null){
            janela.mostrarMensagem("Funcionário não encontrado!");
        } else {
            locacao = new Locacao();
            locacao.setCliente(cliente);
            locacao.setFuncionario(funcionario);
        }
        return cliente != null && funcionario != null;
    }

    // Passo 2
    public boolean getDatasEndereco(){
        Date dataInicio = null;
        Date dataFim = null;
        try {
            dataInicio = new Date(dateFormatter.parse(janela.getDataInicio()).getTime());
            dataFim = new Date(dateFormatter.parse(janela.getDataFim()).getTime());
        } catch (ParseException e) {
            janela.mostrarMensagem("Por favor digite uma data válida!");
            return false;
        }

        String endereco = janela.getEndereco().strip();

        if(dataInicio.after(dataFim)){
            janela.mostrarMensagem("Data de fim não pode ser anterior a data de inicio!");
            return false;
        }
        if (endereco.isBlank()){
            janela.mostrarMensagem("Endereço não pode estar vazio!");
            return false;
        }

        locacao.setInicio(dataInicio);
        locacao.setFim(dataFim);
        locacao.setEndereco(endereco);
        return true;
    }

    // Passo 3
    public List<Artigo> consultarArtigosDisponiveis(){
        return db.buscarArtigos();
    }

    // Passo 4
    public boolean getArtigoLocados(){
        List<ArtigoLocado> artigos = janela.getArtigoLocados();

        if(artigos.size() <= 0) {
            janela.mostrarMensagem("Você deve selecionar pelo menos 1 artigo!");
            return false;
        }
        locacao.setArtigoLocados(artigos);
        return true;
    }
    
    // Passo 5
    public boolean realizarLocacao(){
        String idPagamento = janela.getPagamentoId();
        String forma = janela.getPagamentoForma();
        String info = janela.getPagamentoInfo();
        if(idPagamento.isBlank()){ return false; }
        if(forma.isBlank()){ return false; }
        if(info.isBlank()){ return false; }
        
        Pagamento pagamento = new Pagamento(idPagamento, forma, info);

        locacao.setPagamento(pagamento);

        db.realizarLocacao(locacao);
        return true;
    }

    public boolean realizarDevolucao(){
        return true;
    }

    public double getValorTotal() {
        List<ArtigoLocado> artigos = janela.getArtigoLocados();
        double valorTotal = 0;

        for (ArtigoLocado artigoLocado : artigos) {
            valorTotal += artigoLocado.getValorTotal();
        }

        return valorTotal;
    }
}
