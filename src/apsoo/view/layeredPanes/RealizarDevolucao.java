package apsoo.view.layeredPanes;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TreeMap;
import java.util.Map;

import apsoo.controller.RealizarDevolucaoController;
import apsoo.view.AJanelaLayer;
import apsoo.view.Janela;
import apsoo.view.extensions.JTextAreaPlaceholder;
import apsoo.view.extensions.JTextFieldPlaceholder;

public class RealizarDevolucao extends AJanelaLayer {
    private Janela janela;
    private Map<String, JComponent> components;

    public RealizarDevolucao(Janela janela){
        this.janela = janela;
        components = new TreeMap<String, JComponent>();
        initializeComponents();
    }

    private void initializeComponents(){
        components.put("lblRegistroDevolucao", new JLabel("Registrar Devolucao", SwingConstants.CENTER));
        components.get("lblRegistroDevolucao").setBounds(465, 150, 350, 35);
        ((JLabel) components.get("lblRegistroDevolucao")).setVerticalTextPosition(SwingConstants.CENTER);
        components.get("lblRegistroDevolucao").setFont(new Font("Arial", Font.BOLD, 22));

        components.put("lblNumeroLocacao", new JLabel("Nº Id. da Locação", SwingConstants.RIGHT));
        components.get("lblNumeroLocacao").setBounds(100, 200, 350, 35);
        ((JLabel) components.get("lblNumeroLocacao")).setVerticalTextPosition(SwingConstants.CENTER);
        components.get("lblNumeroLocacao").setFont(new Font("Arial", Font.PLAIN, 16));

        components.put("numeroLocacao", new JTextFieldPlaceholder("Nº Identificador da Locação"));
        components.get("numeroLocacao").setBounds(465, 200, 350, 35);

        components.put("lblInfoAdicional", new JLabel("Observações", SwingConstants.RIGHT));
        components.get("lblInfoAdicional").setBounds(100, 250, 350, 35);
        ((JLabel) components.get("lblInfoAdicional")).setVerticalTextPosition(SwingConstants.CENTER);
        components.get("lblInfoAdicional").setFont(new Font("Arial", Font.PLAIN, 16));

        components.put("infoAdicional", new JTextAreaPlaceholder("Informações adicionais"));
        components.get("infoAdicional").setBounds(465, 250, 350, 105);

        // Footer
        components.put("lblFooter", new JLabel("", SwingConstants.CENTER));
        components.get("lblFooter").setFont(new Font("Arial", Font.BOLD, 20));
        components.get("lblFooter").setBounds(0, janela.getHeight() - 159, janela.getWidth(), 120);
        components.get("lblFooter").setOpaque(true);
        components.get("lblFooter").setBackground(new Color(54, 57, 67));
        components.get("lblFooter").setForeground(Color.WHITE);

        // Botão Concluir
        components.put("btnProximo", new JButton("CONCLUIR"));
        components.get("btnProximo").setBounds((int)((janela.getWidth() - 200) * 0.87), janela.getHeight() - 139, 200, 80);
        components.get("btnProximo").setBackground(new Color(56, 255, 76));
        components.get("btnProximo").setForeground(Color.BLACK);
        ((JButton) components.get("btnProximo")).setBorderPainted(false);
        ((JButton) components.get("btnProximo")).addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                if (((RealizarDevolucaoController) janela.getController()).realizarDevolucao()) { janela.resetar(); };
            }
        });

        // Botão Voltar
        components.put("btnAnterior", new JButton("VOLTAR"));
        components.get("btnAnterior").setBounds((int)((janela.getWidth() - 200) * 0.13), janela.getHeight() - 139, 200, 80);
        components.get("btnAnterior").setBackground(new Color(186, 186, 186));
        components.get("btnAnterior").setForeground(Color.BLACK);
        ((JButton) components.get("btnAnterior")).setBorderPainted(false);
        ((JButton) components.get("btnAnterior")).addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                janela.changeLayeredPane(TelaDaJanela.MENU_PRINCIPAL);
            }
        });

        for (String componentName : components.keySet()) {
            add(components.get(componentName));
        }
    }

    public void updateTela(){}

    public String getLocacaoId(){
        return ((JTextFieldPlaceholder) components.get("numeroLocacao")).getText();
    }
    
    public String getObservacoes(){
        return ((JTextAreaPlaceholder) components.get("infoAdicional")).getText();
    }
}
