package apsoo.view.layeredPanes;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import java.util.TreeMap;
import java.util.Map;

import apsoo.view.AJanelaLayer;
import apsoo.view.Janela;
import apsoo.view.extensions.JTextFieldPlaceholder;

public class TelaInicial extends AJanelaLayer {
    private Janela janela;
    private Map<String, JComponent> components = new TreeMap<String, JComponent>();

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

        

        // Botão Continuar
        components.put("btnProximo", new JButton("CONTINUAR"));
        components.get("btnProximo").setBounds((int)((janela.getWidth() - 200) * 0.87), janela.getHeight() - 139, 200, 80);
        components.get("btnProximo").setBackground(new Color(56, 255, 76));
        components.get("btnProximo").setForeground(Color.BLACK);
        ((JButton) components.get("btnProximo")).setBorderPainted(false);
        ((JButton) components.get("btnProximo")).addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                if(janela.getController().getClienteFuncionario()){
                    janela.nextScreen();
                } else {
                    janela.mostrarMensagem("Usuário ou Funcionário não encontrado!");
                }
            }
        });

        // Botão Cancelar
        components.put("btnAnterior", new JButton("CANCELAR"));
        components.get("btnAnterior").setBounds((int)((janela.getWidth() - 200) * 0.13), janela.getHeight() - 139, 200, 80);
        components.get("btnAnterior").setBackground(new Color(186, 186, 186));
        components.get("btnAnterior").setForeground(Color.BLACK);
        ((JButton) components.get("btnAnterior")).setBorderPainted(false);
        ((JButton) components.get("btnAnterior")).addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent event){
                janela.dispatchEvent(new WindowEvent(janela, WindowEvent.WINDOW_CLOSING));  
                janela.previousScreen();
            }
        });

        // Footer
        components.put("lblFooter", new JLabel(String.format("<html>Passo %d/%d<br/>&#0;</html>", 1, 5), SwingConstants.CENTER));
        components.get("lblFooter").setFont(new Font("Arial", Font.BOLD, 20));
        components.get("lblFooter").setBounds(0, janela.getHeight() - 159, janela.getWidth(), 120);
        components.get("lblFooter").setOpaque(true);
        components.get("lblFooter").setBackground(new Color(54, 57, 67));
        components.get("lblFooter").setForeground(Color.WHITE);

        for (String componentName : components.keySet()) {
            add(components.get(componentName));
        }
    }

    public String getClienteCpf(){
        String cpf = null;

        try { cpf = ((JTextFieldPlaceholder) components.get("clienteCPF")).getText(); }
        catch (Exception e) { e.printStackTrace(); }
        
        return cpf;
    }

    public String getFuncionarioCpf(){
        String cpf = null;

        try { cpf = ((JTextFieldPlaceholder) components.get("funcionarioCPF")).getText(); }
        catch (Exception e) { System.out.println("Não foi possível criar uma instância de cliente! Retornando null"); }
        
        return cpf;
    }

    public void updateTela(){
        components.get("btnAnterior").setVisible(false);
        components.get("btnAnterior").setVisible(true);
        components.get("btnAnterior").repaint();
    }
}
