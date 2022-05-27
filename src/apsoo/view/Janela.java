package apsoo.view;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.*;

import apsoo.model.Artigo;
import apsoo.model.ArtigoLocado;
import apsoo.model.Pagamento;
//import apsoo.controller.SisLoc;
import apsoo.view.layeredPanes.CarrinhoArtigos;
import apsoo.view.layeredPanes.DataLocacao;
import apsoo.view.layeredPanes.MenuArtigos;
import apsoo.view.layeredPanes.RegistroPagamento;
import apsoo.view.layeredPanes.TelaInicial;

public class Janela extends JFrame {
    private static final short WIDTH = 1280;
    private static final short HEIGHT = 720;
    private short telaAtual = 0;
    private List<AJanelaLayer> layeredPanes = new ArrayList<AJanelaLayer>();
    //private SisLoc controller;

    public Janela(){
        //this.controller = new SisLoc();
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
        layeredPanes.add(new TelaInicial(this));        // 0
        layeredPanes.add(new DataLocacao(this));        // 1
        layeredPanes.add(new MenuArtigos(this));        // 2
        layeredPanes.add(new CarrinhoArtigos(this));    // 3
        layeredPanes.add(new RegistroPagamento(this));  // 4

        changeLayeredPane();
    }

    /**
     * Avança para a próxima tela na lista caso não esteja na última
     */
    public void nextScreen(){
        if(telaAtual >= layeredPanes.size() - 1) return;
        telaAtual++;
        changeLayeredPane();
    }

    /**
     * Retorna para a tela anterior caso não seja a primeira
     */
    public void previousScreen(){
        if(telaAtual <= 0) return;
        telaAtual--;
        changeLayeredPane();
    }

    /**
     * Atualiza a tela para a variável da posição do valor de telaAtual
     */
    private void changeLayeredPane(){
        layeredPanes.get(telaAtual).setVisible(false);
        setLayeredPane(layeredPanes.get(telaAtual));
        layeredPanes.get(telaAtual).setVisible(true);
        layeredPanes.get(telaAtual).updateTela();
    }

    public int getWidth(){
        return WIDTH;
    }

    public int getHeight(){
        return HEIGHT;
    }

    public int getTelaAtual(){
        return telaAtual;
    }

    public String getClienteCpf(){
        return ((TelaInicial) layeredPanes.get(0)).getClienteCpf();
    }

    public String getFuncionarioCpf(){
        return ((TelaInicial) layeredPanes.get(0)).getFuncionarioCpf();
    }

    public Date getDataInicio(){
        return ((DataLocacao) layeredPanes.get(1)).getDataInicio();
    }

    public Date getDataFim(){
        return ((DataLocacao) layeredPanes.get(1)).getDataFim();
    }

    public List<ArtigoLocado> getArtigoLocados(){
        return ((CarrinhoArtigos) layeredPanes.get(3)).getArtigosLocados();
    }

    public Pagamento getPagamento(){
        return ((RegistroPagamento) layeredPanes.get(4)).getPagamento();
    }

    public List<Artigo> getArtigosSelecionados(){
        return ((MenuArtigos) layeredPanes.get(2)).getArtigosSelecionados();
    }

    public void mostrarMensagem(String mensagem){
        JOptionPane.showMessageDialog(this, mensagem, "Resultado", JOptionPane.PLAIN_MESSAGE);
    }
}
