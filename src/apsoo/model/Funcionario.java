package apsoo.model;

public class Funcionario extends Pessoa {
    private String senha;
    private double salario;

    public Funcionario(String nome, String cpf, String senha, double salario){
        super(nome, cpf);
        this.senha = senha;
        this.salario = salario;
    }

    public String getSenha(){
        return senha;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }

    public double getSalario(){
        return salario;
    }

    public void getSalario(double salario){
        this.salario = salario;
    }
}
