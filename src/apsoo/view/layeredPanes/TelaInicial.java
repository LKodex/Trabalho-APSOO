package apsoo.view.layeredPanes;

import javax.swing.JLabel;
import javax.swing.JComponent;
import javax.swing.SwingConstants;
import javax.swing.JLayeredPane;

import java.awt.Font;

import java.util.HashMap;
import java.util.Map;

import apsoo.view.Janela;
import apsoo.view.extensions.JTextFieldPlaceholder;

public class TelaInicial extends JLayeredPane {
    private Janela janela;
    private Map<String, JComponent> components = new HashMap<String, JComponent>();

    public TelaInicial(Janela janela){
        this.janela = janela;
        initializeComponents();
    }

    private void initializeComponents(){
        components.put("lblClienteCPF", new JLabel("CPF do Cliente", SwingConstants.CENTER));
        components.get("lblClienteCPF").setBounds(465, 170, 350, 35);
        ((JLabel) components.get("lblClienteCPF")).setVerticalTextPosition(SwingConstants.CENTER);
        components.get("lblClienteCPF").setFont(new Font("Arial", Font.BOLD, 22));
        
        components.put("clienteCPF", new JTextFieldPlaceholder("CPF..."));
        components.get("clienteCPF").setBounds(465, 220, 350, 35);

        components.put("lblFuncionarioCPF", new JLabel("CPF do Funcionario Atendente", SwingConstants.CENTER));
        components.get("lblFuncionarioCPF").setBounds(465, 310, 350, 35);
        ((JLabel) components.get("lblFuncionarioCPF")).setVerticalTextPosition(SwingConstants.CENTER);
        components.get("lblFuncionarioCPF").setFont(new Font("Arial", Font.BOLD, 22));

        components.put("funcionarioCPF", new JTextFieldPlaceholder("CPF..."));
        components.get("funcionarioCPF").setBounds(465, 360, 350, 35);

        for (String componentName : components.keySet()) {
            add(components.get(componentName));
        }
    }

    public void updateComponents(){
        disableComponents();
        enableComponents();
    }

    public void disableComponents(){
        for (String componentName : components.keySet()) {
            components.get(componentName).setVisible(false);
        }
    }

    public void enableComponents(){
        for (String componentName : components.keySet()) {
            components.get(componentName).setVisible(true);
        }
    }
}
