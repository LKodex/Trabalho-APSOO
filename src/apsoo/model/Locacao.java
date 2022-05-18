package apsoo.model;

import java.util.Date;
import java.util.List;

public class Locacao {
    private Date dataReservada;
    private Date inicio;
    private Date fim;
    private String endereco;
    private Funcionario funcionario;
    private Cliente cliente;
    private Pagamento pagamento;
    private List<ArtigoLocado> artigoLocados;

    public Locacao(Date inicio, Date fim, String endereco){
        // TODO: atribuir data e hora atual á dataReservada
        this.inicio = inicio;
        this.fim = fim;
        this.endereco = endereco;
    }

    public Locacao(Date inicio, Date fim, String endereco, Funcionario funcionario, Cliente cliente, Pagamento pagamento){
        // TODO: atribuir data e hora atual á dataReservada
        this.inicio = inicio;
        this.fim = fim;
        this.endereco = endereco;
        this.funcionario = funcionario;
        this.cliente = cliente;
        this.pagamento = pagamento;
    }

    public Locacao(Date inicio, Date fim, String endereco, Funcionario funcionario, Cliente cliente, Pagamento pagamento, List<ArtigoLocado> artigoLocados){
        // TODO: atribuir data e hora atual á dataReservada
        this.inicio = inicio;
        this.fim = fim;
        this.endereco = endereco;
        this.funcionario = funcionario;
        this.cliente = cliente;
        this.pagamento = pagamento;
        this.artigoLocados = artigoLocados;
    }

    public void confirmarLocacao(List<ArtigoLocado> artigoLocados, Funcionario funcionario, Pagamento pagamento){
        this.artigoLocados = artigoLocados;
        this.funcionario = funcionario;
        this.pagamento = pagamento;
    }

    public Date getDataReservada() {
        return dataReservada;
    }
    public void setDataReservada(Date dataReservada) {
        this.dataReservada = dataReservada;
    }
    public Date getInicio() {
        return inicio;
    }
    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }
    public Date getFim() {
        return fim;
    }
    public void setFim(Date fim) {
        this.fim = fim;
    }
    public String getEndereco() {
        return endereco;
    }
    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
