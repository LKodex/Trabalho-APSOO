package apsoo.model;

public class Pagamento {
    private int id;
    private String metodo;
    private String observacao;

    public Pagamento(int id, String metodo, String observacao){
        this.id = id;
        this.metodo = metodo;
        this.observacao = observacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
