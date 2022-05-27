package apsoo.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import java.sql.Date;

import apsoo.model.Artigo;
import apsoo.model.ArtigoLocado;
import apsoo.model.Cliente;
import apsoo.model.Funcionario;
import apsoo.model.Locacao;
import apsoo.model.Pagamento;
import apsoo.controller.SisLoc;
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
    private SisLoc controller;

    private Locacao locacao;
    private Pagamento pagamento;
    private List<ArtigoLocado> listaArtigo;
    private Funcionario funcionario;
    private Cliente cliente;

    public Janela(){
        this.controller = new SisLoc();
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

    // Passo 1/5
    public boolean getClienteFuncionario(){
        cliente = null;
        funcionario = null;

        cliente = controller.buscarCliente(((TelaInicial) layeredPanes.get(0)).getClienteCpf());
        funcionario = controller.buscarFuncionario(((TelaInicial) layeredPanes.get(0)).getFuncionarioCpf());

        return cliente != null && funcionario != null;
    }

    // Passo 2/5
    public boolean getDatasEndereco(){
        locacao = null;
        Date dataInicio = ((DataLocacao) layeredPanes.get(1)).getDataInicio();
        Date dataFim = ((DataLocacao) layeredPanes.get(1)).getDataFim();
        locacao = new Locacao(
            dataInicio,
            dataFim,
            ((DataLocacao) layeredPanes.get(1)).getEndereco()
            );
        return locacao != null && dataInicio.before(dataFim);
    }

    // Passo 4/5
    public boolean getArtigoLocados(){
        listaArtigo = ((CarrinhoArtigos) layeredPanes.get(3)).getArtigosLocados();
        if (listaArtigo.size() <= 0) {
            listaArtigo = null;
            return false;
        }
        return true;
    }

    public boolean getPagamento(){
        pagamento = null;
        pagamento = new Pagamento(
            ((RegistroPagamento) layeredPanes.get(4)).getIdPagamento(),
            ((RegistroPagamento) layeredPanes.get(4)).getForma(),
            ((RegistroPagamento) layeredPanes.get(4)).getInfo()
        );
        return pagamento != null;
    }

    public List<ArtigoLocado> getListaArtigo(){
        return ((CarrinhoArtigos) layeredPanes.get(3)).getArtigosLocados();
    }

    public List<Artigo> getArtigosSelecionados(){
        return ((MenuArtigos) layeredPanes.get(2)).getArtigosSelecionados();
    }

    public void mostrarMensagem(String mensagem){
        JOptionPane.showMessageDialog(this, mensagem, "Resultado", JOptionPane.PLAIN_MESSAGE);
    }

    public Date getDataInicio(){
        return new Date(locacao.getInicio().getTime());
    }

    public Date getDataFim(){
        return new Date(locacao.getFim().getTime());
    }

    public boolean realizarLocacao(){
        locacao.setCliente(cliente);
        locacao.setFuncionario(funcionario);
        locacao.setPagamento(pagamento);
        locacao.setArtigoLocados(listaArtigo);
        controller.cadastrarLocacao(locacao);
        return true;
    }

    public SisLoc getController(){
        return controller;
    }
}
