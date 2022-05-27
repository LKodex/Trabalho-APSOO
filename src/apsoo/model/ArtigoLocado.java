package apsoo.model;

public class ArtigoLocado {
    private int codigo;
    private int quantidade;
    private double valorDiaria;
    private double valorTotal;

    public ArtigoLocado(int codigo, int quantidade, double valorDiaria){
        this.codigo = codigo;
        this.quantidade = quantidade;
        this.valorDiaria = valorDiaria;
        valorTotal = quantidade * valorDiaria;
    }

    public int getCodigo(){
        return codigo;
    }

    public void setCodigo(int codigo){
        this.codigo = codigo;
    }

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

    public double getValorTotal(){
        return valorTotal;
    }
}
