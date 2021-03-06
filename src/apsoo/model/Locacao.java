package apsoo.model;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

public class Locacao {
    private int id;
    private Date dataReservada;
    private Date inicio;
    private Date fim;
    private String endereco;
    private Funcionario funcionario;
    private Cliente cliente;
    private Pagamento pagamento;
    private List<ArtigoLocado> artigoLocados;

    public Locacao(){
        dataReservada = new Date(Calendar.getInstance().getTimeInMillis());
    }
    
     public Locacao (int newId) {
    	this.id = newId;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
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

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario){
        this.funcionario = funcionario;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }

    public Pagamento getPagamento(){
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento){
        this.pagamento = pagamento;
    }

    public List<ArtigoLocado> getArtigoLocados(){
        return artigoLocados;
    }

    public void setArtigoLocados(List<ArtigoLocado> artigoLocados){
        this.artigoLocados = artigoLocados;
    }
}
