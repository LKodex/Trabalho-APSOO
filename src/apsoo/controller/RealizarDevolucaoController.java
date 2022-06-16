package apsoo.controller;

import apsoo.model.Devolucao;
import apsoo.model.Locacao;
import apsoo.view.Janela;

public class RealizarDevolucaoController extends Controller {

    public RealizarDevolucaoController(Janela janela){
        super(janela);
    }


    public boolean realizarDevolucao(){
        Devolucao devolucaoRegistrada;
        Devolucao devolucao;
        Locacao locacao;

        devolucao = new Devolucao(Integer.parseInt(janela.getLocacaoId()), janela.getDevolucaoObservacoes());
        locacao = db.buscarLocacao(devolucao.getId());
        
        if(locacao == null){
            janela.mostrarMensagem(String.format("Não foi encontrada nenhuma locação com o id %d", devolucao.getId()), "Locação não encontrada");
            return false;
        }

        devolucaoRegistrada = db.buscarDevolucao(devolucao.getId());

        if(devolucaoRegistrada == null){
            janela.mostrarMensagem(String.format("Já existe uma devolução registrada para a locação de id %d", devolucao.getId()), "Devolução já registrada");
            return false;
        }

        db.inserirDevolucao(devolucao);
        return true;
    }
}
