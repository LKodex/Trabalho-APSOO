package apsoo.view.layeredPanes;

public enum TelaDaJanela {
    MENU_PRINCIPAL(0),
    REALIZAR_LOCACAO_TELA_INICIAL(1),
    REALIZAR_LOCACAO_DATA_LOCACAO(2),
    REALIZAR_LOCACAO_MENU_ARTIGOS(3),
    REALIZAR_LOCACAO_CARRINHO_ARTIGOS(4),
    REALIZAR_LOCACAO_REGISTRO_PAGAMENTO(5),
    REALIZAR_DEVOLUCAO_TELA_PRINCIPAL(6);

    public int tela;
    TelaDaJanela(int tela){
        this.tela = tela;
    }
}
