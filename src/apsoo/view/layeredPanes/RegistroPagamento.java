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

import apsoo.controller.RealizarLocacaoController;
import apsoo.view.AJanelaLayer;
import apsoo.view.Janela;
import apsoo.view.extensions.JTextAreaPlaceholder;
import apsoo.view.extensions.JTextFieldPlaceholder;

public class RegistroPagamento extends AJanelaLayer {
    private Janela janela;
    private Map<String, JComponent> components;

    public RegistroPagamento(Janela janela){
        this.janela = janela;
        components = new TreeMap<String, JComponent>();
        initializeComponents();
    }

    private void initializeComponents(){
        components.put("lblRegistroPagamento", new JLabel("Registro do Pagamento", SwingConstants.CENTER));
        components.get("lblRegistroPagamento").setBounds(465, 150, 350, 35);
        ((JLabel) components.get("lblRegistroPagamento")).setVerticalTextPosition(SwingConstants.CENTER);
        components.get("lblRegistroPagamento").setFont(new Font("Arial", Font.BOLD, 22));

        components.put("lblNumeroPagamento", new JLabel("Nº Identificador", SwingConstants.RIGHT));
        components.get("lblNumeroPagamento").setBounds(100, 200, 350, 35);
        ((JLabel) components.get("lblNumeroPagamento")).setVerticalTextPosition(SwingConstants.CENTER);
        components.get("lblNumeroPagamento").setFont(new Font("Arial", Font.PLAIN, 16));

        components.put("numeroPagamento", new JTextFieldPlaceholder("Nº Identificador do Pagamento"));
        components.get("numeroPagamento").setBounds(465, 200, 350, 35);

        components.put("lblFormaPagamento", new JLabel("Forma de pagamento", SwingConstants.RIGHT));
        components.get("lblFormaPagamento").setBounds(100, 250, 350, 35);
        ((JLabel) components.get("lblFormaPagamento")).setVerticalTextPosition(SwingConstants.CENTER);
        components.get("lblFormaPagamento").setFont(new Font("Arial", Font.PLAIN, 16));

        components.put("formaPagamento", new JTextFieldPlaceholder("Forma de Pagamento"));
        components.get("formaPagamento").setBounds(465, 250, 350, 35);
        
        components.put("lblInfoAdicional", new JLabel("Observações", SwingConstants.RIGHT));
        components.get("lblInfoAdicional").setBounds(100, 300, 350, 35);
        ((JLabel) components.get("lblInfoAdicional")).setVerticalTextPosition(SwingConstants.CENTER);
        components.get("lblInfoAdicional").setFont(new Font("Arial", Font.PLAIN, 16));

        components.put("infoAdicional", new JTextAreaPlaceholder("Informações adicionais"));
        components.get("infoAdicional").setBounds(465, 300, 350, 105);

        // Footer
        components.put("lblFooter", new JLabel(String.format("<html>Passo %d/%d<br/>Total: R$0.00</html>", 5, 5), SwingConstants.CENTER));
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
                if (((RealizarLocacaoController) janela.getController()).realizarLocacao()) { janela.resetar(); };
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
                janela.changeLayeredPane(TelaDaJanela.REALIZAR_LOCACAO_CARRINHO_ARTIGOS);
            }
        });

        for (String componentName : components.keySet()) {
            add(components.get(componentName));
        }
    }

    public void updateTela(){
        components.get("btnAnterior").setVisible(false);
        components.get("btnAnterior").setVisible(true);

        ((JLabel) components.get("lblFooter")).setText(String.format("<html>Passo %d/%d<br/>Total: R$%.2f</html>", 5, 5, ((RealizarLocacaoController) janela.getController()).getValorTotal()));
    }

    public String getIdPagamento() {
        return ((JTextFieldPlaceholder) components.get("numeroPagamento")).getText();
    }

    public String getForma() {
        return ((JTextFieldPlaceholder) components.get("formaPagamento")).getText();
    }

    public String getInfo() {
        return ((JTextAreaPlaceholder) components.get("infoAdicional")).getText();
    }
}
