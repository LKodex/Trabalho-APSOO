package apsoo.view.layeredPanes;

import javax.swing.JLayeredPane;

import apsoo.view.extensions.JTextFieldPlaceholder;

import java.util.HashMap;
import java.util.Map;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class TelaInicial extends JLayeredPane {
    private JFrame janela;
    private Map<String, JComponent> components = new HashMap<String, JComponent>();

    public TelaInicial(JFrame janela){
        this.janela = janela;
    }

    private void initializeComponents(){
        components.put("clienteCPF", new JTextFieldPlaceholder("CPF..."));
        components.get("clienteCPF").setBounds(465, 240, 350, 35);

        components.put("funcionarioCPF", new JTextFieldPlaceholder("CPF..."));
        components.get("funcionarioCPF").setBounds(465, 240, 350, 35);
    }
}
