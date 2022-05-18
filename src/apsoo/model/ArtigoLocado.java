package apsoo.model;

public class ArtigoLocado {
    private int quantidade;
    private double valorDiaria;

    public int getQuantidade(){
        return quantidade;
    }

    public void selecionarQuantidade(int quantidade){
        this.quantidade = quantidade;
    }

    public double getValorDiaria(){
        return valorDiaria;
    }

    public void setValorDiaria(double valorDiaria){
        this.valorDiaria = valorDiaria;
    }
}
