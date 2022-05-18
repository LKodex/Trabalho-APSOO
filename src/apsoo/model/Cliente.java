package apsoo.model;

public class Cliente extends Pessoa {
    private String celular;

    public Cliente(String nome, String cpf, String celular){
        super(nome, cpf);
        this.celular = celular;
    }

    public String getCelular(){
        return celular;
    }

    public void setCelular(String celular){
        this.celular = celular;
    }
}
