package apsoo.view.layeredPanes;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.SwingConstants;
import javax.swing.JLayeredPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.HashMap;
import java.util.Map;

import apsoo.view.Janela;

public class DataLocacao extends JLayeredPane {
    private Janela janela;
    private Map<String, JComponent> components = new HashMap<String, JComponent>();

    public DataLocacao(Janela janela){
        this.janela = janela;
        initializeComponents();
    }

    private void initializeComponents(){
        // Footer
        components.put("lblFooter", new JLabel(String.format("<html>%d/%d<br/>&#0;</html>", 2, 5), SwingConstants.CENTER));
        components.get("lblFooter").setFont(new Font("Arial", Font.BOLD, 20));
        components.get("lblFooter").setBounds(0, janela.getHeight() - 159, janela.getWidth(), 120);
        components.get("lblFooter").setOpaque(true);
        components.get("lblFooter").setBackground(new Color(54, 57, 67));
        components.get("lblFooter").setForeground(Color.WHITE);

        // Botão Continuar
        components.put("btnProximo", new JButton("CONTINUAR"));
        components.get("btnProximo").setBounds((int)((janela.getWidth() - 200) * 0.87), janela.getHeight() - 139, 200, 80);
        components.get("btnProximo").setBackground(new Color(56, 255, 76));
        components.get("btnProximo").setForeground(Color.BLACK);
        ((JButton) components.get("btnProximo")).setBorderPainted(false);
        ((JButton) components.get("btnProximo")).addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                janela.nextScreen();
            }
        });

        // Botão Cancelar
        components.put("btnAnterior", new JButton("VOLTAR"));
        components.get("btnAnterior").setBounds((int)((janela.getWidth() - 200) * 0.13), janela.getHeight() - 139, 200, 80);
        components.get("btnAnterior").setBackground(new Color(186, 186, 186));
        components.get("btnAnterior").setForeground(Color.BLACK);
        ((JButton) components.get("btnAnterior")).setBorderPainted(false);
        ((JButton) components.get("btnAnterior")).addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                janela.previousScreen();
            }
        });

        for (String componentName : components.keySet()) {
            add(components.get(componentName));
        }
    }
}
