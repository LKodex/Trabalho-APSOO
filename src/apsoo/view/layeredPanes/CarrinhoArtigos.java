package apsoo.view.layeredPanes;

import javax.swing.JLabel;
import javax.swing.JButton;
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
import apsoo.model.ArtigoLocado;
import apsoo.view.AJanelaLayer;
import apsoo.view.Janela;
import apsoo.view.extensions.JTextFieldPlaceholder;

public class CarrinhoArtigos extends AJanelaLayer {
    private Janela                      janela;
    private Map<String, JComponent>     components;
    private List<JLabel>                artigosLabel;
    private List<JTextFieldPlaceholder> artigosInput;

    public CarrinhoArtigos(Janela janela){
        components      =   new TreeMap<String, JComponent>();
        artigosLabel    =   new ArrayList<JLabel>();
        artigosInput    =   new ArrayList<JTextFieldPlaceholder>();
        this.janela     =   janela;
        initializeComponents();
    }

    private void initializeComponents(){
        components.put("lblCarrinho", new JLabel("Carrinho de Artigos", SwingConstants.CENTER));
        components.get("lblCarrinho").setBounds(465, 50, 350, 35);
        ((JLabel) components.get("lblCarrinho")).setVerticalTextPosition(SwingConstants.CENTER);
        components.get("lblCarrinho").setFont(new Font("Arial", Font.BOLD, 22));

        // Footer
        components.put("lblFooter", new JLabel(String.format("<html>Passo %d/%d<br/>&#0;</html>", 4, 5), SwingConstants.CENTER));
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
                if(janela.getController().getArtigoLocados()){ janela.nextScreen(); }
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

    public List<ArtigoLocado> getArtigosLocados(List<Artigo> artigoLista){
        List<ArtigoLocado> artigosLocados = new ArrayList<ArtigoLocado>();
        int daquelejeitao = 0;
        for (Artigo artigo : artigoLista) {
            artigosLocados.add(new ArtigoLocado(artigo.getCodigo(), Integer.parseInt(artigosInput.get(daquelejeitao).getText()), artigo.getValorDiaria()));
            daquelejeitao++;
        }
        if(artigosLocados.size() <= 0) return null;
        return artigosLocados;
    }

    public void updateTela(List<Artigo> artigoLista){
        // Remove elementos antigos da tela
        for(JLabel jLabel : artigosLabel){ remove(jLabel); }
        for(JTextFieldPlaceholder jPlaceholder : artigosInput){ remove(jPlaceholder); }

        // Redefine as listas de componentes
        artigosLabel = new ArrayList<JLabel>();
        artigosInput = new ArrayList<JTextFieldPlaceholder>();

        for (Artigo artigo : artigoLista) {
            artigosLabel.add(new JLabel(String.format("%d | %s | R$%.2f | Qntd: %d", artigo.getCodigo(), artigo.getNome(), artigo.getValorDiaria(), artigo.getEstoqueTotal())));
            artigosInput.add(new JTextFieldPlaceholder("0"));
        }
        
        for (int i = 0; i < artigoLista.size(); i++) {
            artigosLabel.get(i).setBounds(120 + 350 * (i / 8), 120 + 50 * (i % 8), 300, 40);
            artigosInput.get(i).setBounds(70 + 350 * (i / 8), 120 + 50 * (i % 8), 40, 40);
        }

        // Adiciona componentes de listagem a tela
        for(JLabel jLabel : artigosLabel){ add(jLabel); }
        for(JTextFieldPlaceholder jPlaceholder : artigosInput){ add(jPlaceholder); }

        for(JLabel artigo : artigosLabel){
            artigo.setVisible(false);
            artigo.setVisible(true);
        }
        for(JTextFieldPlaceholder jPlaceholder : artigosInput){
            jPlaceholder.setVisible(false);
            jPlaceholder.setVisible(true);
        }
        components.get("btnAnterior").setVisible(false);
        components.get("btnAnterior").setVisible(true);
    }
}


    @Override
    public void updateTela() {
        // TODO Auto-generated method stub
        
    }