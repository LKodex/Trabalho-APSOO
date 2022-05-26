package apsoo;

import apsoo.database.GerBD;
import apsoo.model.Cliente;

public class Main{
    public static void main(String[]args){
        GerBD gerente=GerBD.getInstance();
        Cliente cli = gerente.buscarCliente("123");

        try {
            System.out.println(cli.getNome());
        } catch (Exception e) {
            System.out.println("null");
            //TODO: handle exception
        }
    }
}