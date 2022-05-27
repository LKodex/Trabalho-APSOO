package apsoo.view.layeredPanes;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.TreeMap;
import java.util.Date;
import java.util.Map;

import apsoo.view.AJanelaLayer;
import apsoo.view.Janela;
import apsoo.view.extensions.JTextFieldPlaceholder;

public class DataLocacao extends AJanelaLayer {
    private Janela janela;
    private Map<String, JComponent> components = new TreeMap<String, JComponent>();

    public DataLocacao(Janela janela){
        this.janela = janela;
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
                if(janela.getDatasEndereco()){
                    janela.nextScreen();
                } else {
                    janela.mostrarMensagem("Algo deu errado! Por favor preencha todos os campos e insira uma data de inicio anterior á final");
                }
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

    public Date getDataInicio(){
        Date dataInicio = null;
        try {
            dataInicio = new SimpleDateFormat("dd-MM-YYYY").parse(((JTextFieldPlaceholder) components.get("dataInicio")).getText());
        } catch (Exception e) {
            System.out.println("Não foi possível criar uma instância de data do inicio! Retornando null");
        }
        return dataInicio;
    }

    public Date getDataFim(){
        Date dataFim = null;
        try {
            dataFim = new SimpleDateFormat("dd-MM-YYYY").parse(((JTextFieldPlaceholder) components.get("dataFim")).getText());
        } catch (Exception e) {
            System.out.println("Não foi possível criar uma instância de data do fim! Retornando null");
        }
        return dataFim;
    }

    public void updateTela(){
        components.get("btnAnterior").setVisible(false);
        components.get("btnAnterior").setVisible(true);
    }

    public String getEndereco() {
        return ((JTextFieldPlaceholder) components.get("endereco")).getText();
    }
}
