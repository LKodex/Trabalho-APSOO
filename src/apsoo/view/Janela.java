package apsoo.view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

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
    private List<JLayeredPane> layeredPanes = new ArrayList<JLayeredPane>();
    private Map<String, JComponent> footerComponents = new HashMap<String, JComponent>();
    private Janela that = this;
    //private SisLoc controller;

    public Janela(){
        //this.controller = new SisLoc();
        initializeWindow();
        initializeFooter();
        initializeLayeredPanes();
    }

    /**
     * Inicializa a janela definindo seu tamanho, titulo e tornando visível para o usuário
     */
    private void initializeWindow(){
        setSize(WIDTH, HEIGHT);
        setTitle("Cadastro de Cliente");
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
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

        for (int i = 0; i < layeredPanes.size(); i++) {
            for (String componentName : footerComponents.keySet()) {
                layeredPanes.get(i).add(footerComponents.get(componentName));
            }
        }

        changeLayeredPane();
    }

    /**
     * Avança para a próxima tela na lista caso não esteja na última
     */
    public void nextScreen(){
        if(telaAtual >= layeredPanes.size()) return;
        telaAtual++;
        if(telaAtual == getTelaTotal()){ ((JButton) footerComponents.get("btnProximo")).setText("CONCLUIR"); }
        else { ((JButton) footerComponents.get("btnProximo")).setText("CONTINUAR"); }
        changeLayeredPane();
    }

    /**
     * Retorna para a tela anterior caso não seja a primeira
     */
    public void previousScreen(){
        if(telaAtual <= 0) return;
        telaAtual--;
        if(telaAtual == getTelaTotal()){ ((JButton) footerComponents.get("btnProximo")).setText("CONCLUIR"); }
        else { ((JButton) footerComponents.get("btnProximo")).setText("CONTINUAR"); }
        changeLayeredPane();
    }

    /**
     * Atualiza a tela para a variável da posição do valor de telaAtual
     */
    private void changeLayeredPane(){
        setVisible(false);
        setLayeredPane(layeredPanes.get(telaAtual));
        setVisible(true);
    }

    public int getWidth(){ return WIDTH; }

    public int getHeight(){ return HEIGHT; }

    public int getTelaAtual(){ return telaAtual + 1; }

    public int getTelaTotal(){ return layeredPanes.size(); }

    private void initializeFooter(){
        // Footer
        footerComponents.put("lblFooter", new JLabel(String.format("<html>Passo %d/%d<br/>&#0;</html>", this.getTelaAtual() + 1, 5), SwingConstants.CENTER));
        footerComponents.get("lblFooter").setFont(new Font("Arial", Font.BOLD, 20));
        footerComponents.get("lblFooter").setBounds(0, this.getHeight() - 159, this.getWidth(), 120);
        footerComponents.get("lblFooter").setOpaque(true);
        footerComponents.get("lblFooter").setBackground(new Color(54, 57, 67));
        footerComponents.get("lblFooter").setForeground(Color.WHITE);

        // Botão Continuar
        footerComponents.put("btnProximo", new JButton("CONTINUAR"));
        footerComponents.get("btnProximo").setBounds((int)((this.getWidth() - 200) * 0.87), this.getHeight() - 139, 200, 80);
        footerComponents.get("btnProximo").setBackground(new Color(56, 255, 76));
        footerComponents.get("btnProximo").setForeground(Color.BLACK);
        ((JButton) footerComponents.get("btnProximo")).setBorderPainted(false);
        ((JButton) footerComponents.get("btnProximo")).addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                that.nextScreen();
            }
        });

        // Botão Cancelar
        footerComponents.put("btnAnterior", new JButton("CANCELAR"));
        footerComponents.get("btnAnterior").setBounds((int)((this.getWidth() - 200) * 0.13), this.getHeight() - 139, 200, 80);
        footerComponents.get("btnAnterior").setBackground(new Color(186, 186, 186));
        footerComponents.get("btnAnterior").setForeground(Color.BLACK);
        ((JButton) footerComponents.get("btnAnterior")).setBorderPainted(false);
        ((JButton) footerComponents.get("btnAnterior")).addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                that.previousScreen();
            }
        });
    }

    public void updateFooter(){
        // Footer
        ((JButton) footerComponents.get("lblFooter")).setText(String.format("<html>Passo %d/%d<br/>&#0;</html>", this.getTelaAtual(), this.getTelaTotal()));
        footerComponents.get("lblFooter").setVisible(false);
        footerComponents.get("lblFooter").setVisible(true);

        footerComponents.get("btnAnterior").setVisible(false);
        footerComponents.get("btnAnterior").setVisible(true);
        
        footerComponents.get("btnProximo").setVisible(false);
        footerComponents.get("btnProximo").setVisible(true);
    }
}
