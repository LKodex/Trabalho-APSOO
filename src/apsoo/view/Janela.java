package apsoo.view;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.*;

import apsoo.model.Artigo;
import apsoo.model.ArtigoLocado;
import apsoo.controller.Controller;
import apsoo.controller.RealizarLocacaoController;
import apsoo.view.layeredPanes.CarrinhoArtigos;
import apsoo.view.layeredPanes.DataLocacao;
import apsoo.view.layeredPanes.MenuArtigos;
import apsoo.view.layeredPanes.RealizarDevolucao;
import apsoo.view.layeredPanes.RegistroPagamento;
import apsoo.view.layeredPanes.TelaDaJanela;
import apsoo.view.layeredPanes.TelaInicial;
import apsoo.view.layeredPanes.TelaPrincipal;

public class Janela extends JFrame {
    private static final short WIDTH = 1280;
    private static final short HEIGHT = 720;
    private Map<TelaDaJanela, AJanelaLayer> layeredPanes;
    private Controller controller;

    public Janela(){
        controller = new RealizarLocacaoController(this);
        layeredPanes = new TreeMap<TelaDaJanela, AJanelaLayer>();
        initializeWindow();
        initializeLayeredPanes();
    }

    /**
     * Inicializa a janela definindo seu tamanho, titulo e tornando visível para o usuário
     */
    private void initializeWindow(){
        setSize(WIDTH, HEIGHT);
        setTitle("Realizar Locação");
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Inicializa os painéis com coteúdos e o adicionam na lista layeredPanes
     * Após isso define o conteúdo atual da tela para o primeiro item da lista
     */
    private void initializeLayeredPanes(){
        layeredPanes.put(TelaDaJanela.MENU_PRINCIPAL, new TelaPrincipal(this));
        layeredPanes.put(TelaDaJanela.REALIZAR_LOCACAO_TELA_INICIAL, new TelaInicial(this));
        layeredPanes.put(TelaDaJanela.REALIZAR_LOCACAO_DATA_LOCACAO, new DataLocacao(this));
        layeredPanes.put(TelaDaJanela.REALIZAR_LOCACAO_MENU_ARTIGOS, new MenuArtigos(this));
        layeredPanes.put(TelaDaJanela.REALIZAR_LOCACAO_CARRINHO_ARTIGOS, new CarrinhoArtigos(this));
        layeredPanes.put(TelaDaJanela.REALIZAR_LOCACAO_REGISTRO_PAGAMENTO, new RegistroPagamento(this));
        layeredPanes.put(TelaDaJanela.REALIZAR_DEVOLUCAO_TELA_PRINCIPAL, new RealizarDevolucao(this));

        changeLayeredPane(TelaDaJanela.MENU_PRINCIPAL);
    }
    
    public void changeLayeredPane(TelaDaJanela telaDaJanela){
        layeredPanes.get(telaDaJanela).setVisible(false);
        setLayeredPane(layeredPanes.get(telaDaJanela));
        layeredPanes.get(telaDaJanela).updateTela();
        layeredPanes.get(telaDaJanela).setVisible(true);
    }

    public int getWidth(){ return WIDTH; }
    public int getHeight(){ return HEIGHT; }

    // Realizar Locação - Passo 1/5
    public String getClienteCpf(){
        return ((TelaInicial) layeredPanes.get(TelaDaJanela.REALIZAR_LOCACAO_TELA_INICIAL)).getClienteCpf();
    }

    public String getFuncionarioCpf(){
        return ((TelaInicial) layeredPanes.get(TelaDaJanela.REALIZAR_LOCACAO_TELA_INICIAL)).getFuncionarioCpf();
    }

    // Realizar Locação - Passo 2/5
    public String getDataInicio(){
        return ((DataLocacao) layeredPanes.get(TelaDaJanela.REALIZAR_LOCACAO_DATA_LOCACAO)).getDataInicio();
    }

    public String getDataFim(){
        return ((DataLocacao) layeredPanes.get(TelaDaJanela.REALIZAR_LOCACAO_DATA_LOCACAO)).getDataFim();
    }

    public String getEndereco(){
        return ((DataLocacao) layeredPanes.get(TelaDaJanela.REALIZAR_LOCACAO_DATA_LOCACAO)).getEndereco();
    }

    // Realizar Locação - Passo 3/5
    public List<Artigo> getArtigosSelecionados(){
        return ((MenuArtigos) layeredPanes.get(TelaDaJanela.REALIZAR_LOCACAO_MENU_ARTIGOS)).getArtigosSelecionados();
    }

    // Realizar Locação - Passo 4/5
    public List<ArtigoLocado> getArtigoLocados(){
        return ((CarrinhoArtigos) layeredPanes.get(TelaDaJanela.REALIZAR_LOCACAO_CARRINHO_ARTIGOS)).getArtigosLocados();
    }

    // Realizar Locação - Passo 5/5
    public String getPagamentoId(){
        return ((RegistroPagamento) layeredPanes.get(TelaDaJanela.REALIZAR_LOCACAO_REGISTRO_PAGAMENTO)).getIdPagamento();
    }

    public String getPagamentoForma(){
        return ((RegistroPagamento) layeredPanes.get(TelaDaJanela.REALIZAR_LOCACAO_REGISTRO_PAGAMENTO)).getForma();
    }

    public String getPagamentoInfo(){
        return ((RegistroPagamento) layeredPanes.get(TelaDaJanela.REALIZAR_LOCACAO_REGISTRO_PAGAMENTO)).getInfo();
    }

    // Realizar Devolução 1/1
    public String getLocacaoId(){
        return ((RealizarDevolucao) layeredPanes.get(TelaDaJanela.REALIZAR_DEVOLUCAO_TELA_PRINCIPAL)).getLocacaoId();
    }

    public String getDevolucaoObservacoes(){
        return ((RealizarDevolucao) layeredPanes.get(TelaDaJanela.REALIZAR_DEVOLUCAO_TELA_PRINCIPAL)).getObservacoes();
    }


    public void resetar(){
        layeredPanes = new TreeMap<TelaDaJanela, AJanelaLayer>();
        initializeLayeredPanes();
    }

    public void fecharJanela(){
        dispatchEvent(new java.awt.event.WindowEvent(this, java.awt.event.WindowEvent.WINDOW_CLOSING));
    }

    public void mostrarMensagem(String mensagem){
        JOptionPane.showMessageDialog(this, mensagem, "Resultado", JOptionPane.PLAIN_MESSAGE);
    }

    public void mostrarMensagem(String mensagem, String titulo){
        JOptionPane.showMessageDialog(this, mensagem, titulo, JOptionPane.PLAIN_MESSAGE);
    }
    
    public void mostrarMensagem(String mensagem, int messageType){
        JOptionPane.showMessageDialog(this, mensagem, "Resultado", messageType);
    }
    
    public void mostrarMensagem(String mensagem, String titulo, int messageType){
        JOptionPane.showMessageDialog(this, mensagem, titulo, messageType);
    }

    public Controller getController(){
        return controller;
    }

    public void setController(Controller controller){
        this.controller = controller;
    }
}
