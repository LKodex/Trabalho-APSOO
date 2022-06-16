package apsoo.controller;

import apsoo.database.GerBD;
import apsoo.view.Janela;

public abstract class Controller {
    protected GerBD db;
    protected Janela janela;

    public Controller(Janela janela){
        this.janela = janela;
        this.db     = GerBD.getInstance();
    }
}
