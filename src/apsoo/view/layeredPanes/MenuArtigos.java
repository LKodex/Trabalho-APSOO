package apsoo.view.layeredPanes;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.List;
import java.util.Map;

import apsoo.model.Artigo;
import apsoo.view.AJanelaLayer;
import apsoo.view.Janela;

public class MenuArtigos extends AJanelaLayer {
    private Janela janela;
    private Map<String, JComponent> components = new TreeMap<String, JComponent>();
    private List<Artigo> artigoLista = new ArrayList<Artigo>();
    private List<JCheckBox> artigosCheckBox = new ArrayList<JCheckBox>();

    public MenuArtigos(Janela janela){
        this.janela = janela;
        artigoLista = janela.getController().consultarArtigosDisponiveis();
        initializeComponents();
    }

    private void initializeComponents(){
        components.put("lblMenuEscolha", new JLabel("Menu de Escolha de Artigos", SwingConstants.CENTER));
        components.get("lblMenuEscolha").setBounds(465, 50, 350, 35);
        ((JLabel) components.get("lblMenuEscolha")).setVerticalTextPosition(SwingConstants.CENTER);
        components.get("lblMenuEscolha").setFont(new Font("Arial", Font.BOLD, 22));

        for (Artigo artigo : artigoLista) {
            artigosCheckBox.add(new JCheckBox(String.format("%d | %s | R$%.2f | Qntd: %d", artigo.getCodigo(), artigo.getNome(), artigo.getValorDiaria(), artigo.getEstoqueTotal())));
        }
        
        for (int i = 0; i < artigoLista.size(); i++) {
            artigosCheckBox.get(i).setBounds(170 + 300 * (i / 8), 120 + 50 * (i % 8), 300, 40);
        }

        // Footer
        components.put("lblFooter", new JLabel(String.format("<html>Passo %d/%d<br/>&#0;</html>", 3, 5), SwingConstants.CENTER));
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

        for(JCheckBox jCheckBox : artigosCheckBox){
            add(jCheckBox);
        }
    }

    public List<Artigo> getArtigosSelecionados(){
        List<Artigo> artigosSelecionados = new ArrayList<Artigo>();
        int meodeosatequandoisso = 0;
        for (Artigo artigo : artigoLista) {
            if (artigosCheckBox.get(meodeosatequandoisso).isSelected()) {
                artigosSelecionados.add(artigo);
            }
            meodeosatequandoisso++;
        }
        return artigosSelecionados;
    }

    public void updateTela(){
        for(JCheckBox jCheckBox : artigosCheckBox){
            jCheckBox.setVisible(false);
            jCheckBox.setVisible(true);
        }
        components.get("btnAnterior").setVisible(false);
        components.get("btnAnterior").setVisible(true);
    }
}
