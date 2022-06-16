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
import apsoo.controller.RealizarLocacaoController;
import apsoo.view.AJanelaLayer;
import apsoo.view.Janela;

public class TelaPrincipal extends AJanelaLayer {
    private Janela janela;
    private Map<String, JComponent> components;

    public TelaPrincipal(Janela janela){
        this.janela = janela;
        components = new TreeMap<String, JComponent>();
        initializeComponents();
    }

    private void initializeComponents(){
        // Botão Realizar Locação
        components.put("btnRealizarLocacao", new JButton("Realizar Locação"));
        components.get("btnRealizarLocacao").setBounds((int)((janela.getWidth() - 200) * 0.5), 150, 200, 80);
        components.get("btnRealizarLocacao").setBackground(Color.GRAY);
        components.get("btnRealizarLocacao").setForeground(Color.BLACK);
        ((JButton) components.get("btnRealizarLocacao")).setBorderPainted(false);
        ((JButton) components.get("btnRealizarLocacao")).addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                janela.setController(new RealizarLocacaoController(janela));
                janela.changeLayeredPane(TelaDaJanela.REALIZAR_LOCACAO_TELA_INICIAL);
            }
        });

        // Botão Devolução
        components.put("btnRegistrarDevolucao", new JButton("Registrar Devolução"));
        components.get("btnRegistrarDevolucao").setBounds((int)((janela.getWidth() - 200) * 0.5), 245, 200, 80);
        components.get("btnRegistrarDevolucao").setBackground(Color.GRAY);
        components.get("btnRegistrarDevolucao").setForeground(Color.BLACK);
        ((JButton) components.get("btnRegistrarDevolucao")).setBorderPainted(false);
        ((JButton) components.get("btnRegistrarDevolucao")).addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                janela.setController(new RealizarDevolucaoController(janela));
                janela.changeLayeredPane(TelaDaJanela.REALIZAR_DEVOLUCAO_TELA_PRINCIPAL);
            }
        });

        // Footer
        components.put("lblFooter", new JLabel(String.format("<html>Passo %d/%d<br/>Total: R$0.00</html>", 5, 5), SwingConstants.CENTER));
        components.get("lblFooter").setFont(new Font("Arial", Font.BOLD, 20));
        components.get("lblFooter").setBounds(0, janela.getHeight() - 159, janela.getWidth(), 120);
        components.get("lblFooter").setOpaque(true);
        components.get("lblFooter").setBackground(new Color(54, 57, 67));
        components.get("lblFooter").setForeground(Color.WHITE);

        // Botão Fechar Janela
        components.put("btnFechar", new JButton("FECHAR"));
        components.get("btnFechar").setBounds((int)((janela.getWidth() - 200) * 0.5), janela.getHeight() - 139, 200, 80);
        components.get("btnFechar").setBackground(new Color(186, 186, 186));
        components.get("btnFechar").setForeground(Color.BLACK);
        ((JButton) components.get("btnFechar")).setBorderPainted(false);
        ((JButton) components.get("btnFechar")).addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                janela.fecharJanela();
            }
        });

        for (String componentName : components.keySet()) {
            add(components.get(componentName));
        }
    }

    public void updateTela(){
        
    }
}
