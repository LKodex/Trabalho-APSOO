package apsoo.view.layeredPanes;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.SwingConstants;
import javax.swing.text.MaskFormatter;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.TreeMap;
import java.util.Map;

import apsoo.view.AJanelaLayer;
import apsoo.view.Janela;
import apsoo.view.extensions.JTextFieldPlaceholder;

public class DataLocacao extends AJanelaLayer {
    private Janela janela;
    private Map<String, JComponent> components;
    private MaskFormatter dateFormatter;

    public DataLocacao(Janela janela){
        this.janela = janela;
        components = new TreeMap<String, JComponent>();
        try { dateFormatter = new MaskFormatter("##/##/####"); }
        catch (ParseException e) { e.printStackTrace(); }
        initializeComponents();
    }

    private void initializeComponents(){
        components.put("lblDataLocacao", new JLabel("Informações da Locação", SwingConstants.CENTER));
        components.get("lblDataLocacao").setBounds(465, 170, 350, 35);
        ((JLabel) components.get("lblDataLocacao")).setVerticalTextPosition(SwingConstants.CENTER);
        components.get("lblDataLocacao").setFont(new Font("Arial", Font.BOLD, 22));
        
        components.put("dataInicio", new JTextFieldPlaceholder("Data de Inicio (dd-MM-AAAA)"));
        components.get("dataInicio").setBounds(465, 220, 350, 35);

        components.put("dataFim", new JTextFieldPlaceholder("Data de Fim (dd-MM-AAAA)"));
        components.get("dataFim").setBounds(465, 270, 350, 35);

        components.put("endereco", new JTextFieldPlaceholder("Endereço"));
        components.get("endereco").setBounds(465, 320, 350, 35);
        
        // Footer
        components.put("lblFooter", new JLabel(String.format("<html>Passo %d/%d<br/>&#0;</html>", 2, 5), SwingConstants.CENTER));
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
                if(janela.getController().getDatasEndereco()){ janela.nextScreen(); }
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
                janela.previousScreen();
            }
        });

        for (String componentName : components.keySet()) {
            add(components.get(componentName));
        }
    }

    public String getDataInicio(){
        return ((JTextFieldPlaceholder) components.get("dataInicio")).getText();
    }

    public String getDataFim(){
        return ((JTextFieldPlaceholder) components.get("dataFim")).getText();
    }
    
    public String getEndereco() {
        return ((JTextFieldPlaceholder) components.get("endereco")).getText();
    }

    public void setDataInicio(String text){
        ((JTextFieldPlaceholder) components.get("dataInicio")).setText(text);
    }

    public void setDataFim(String text){
        ((JTextFieldPlaceholder) components.get("dataFim")).setText(text);
    }
    
    public void setEndereco(String text) {
        ((JTextFieldPlaceholder) components.get("endereco")).setText(text);
    }

    public void updateTela(){
        components.get("btnAnterior").setVisible(false);
        components.get("btnAnterior").setVisible(true);
    }
}
