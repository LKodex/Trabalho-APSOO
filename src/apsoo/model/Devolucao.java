package apsoo.model;

import java.sql.Date;
import java.util.Calendar;

public class Devolucao{
    private int id;
    private Date dataDevolucao;
    private String observacoes;

    public Devolucao(int num, String obs){
        this.dataDevolucao = new Date(Calendar.getInstance().getTimeInMillis());
        this.id=num;
        this.observacoes=obs;
    }

    public Date getData() {
        return this.dataDevolucao;
    }

    public void setData(Date dataDevolucao){
        this.dataDevolucao = dataDevolucao;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getObservacao() {
        return this.observacoes;
    }

    public void setObservacao(String observacoes) {
        this.observacoes = observacoes;
    }

}