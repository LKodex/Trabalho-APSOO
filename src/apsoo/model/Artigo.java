package apsoo.model;

public class Artigo {
    private int codigo;
    private String nome;
    private double valorDiaria;
    private int estoqueTotal;

    public Artigo(int codigo, String nome, double valorDiaria, int estoqueTotal){
        this.codigo = codigo;
        this.nome = nome;
        this.valorDiaria = valorDiaria;
        this.estoqueTotal = estoqueTotal;
    }
    
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public int getEstoqueTotal() {
        return estoqueTotal;
    }

    public void setEstoqueTotal(int estoqueTotal) {
        this.estoqueTotal = estoqueTotal;
    }
}
