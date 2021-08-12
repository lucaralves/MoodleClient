package com.example.projectjavaandroid2.model.partial;

import java.util.ArrayList;

public class ListaAvaliacaoPartial {

    private ArrayList<AvaliacaoPartial> avaliacoes;

    public ListaAvaliacaoPartial(ArrayList<AvaliacaoPartial> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public ArrayList<AvaliacaoPartial> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(ArrayList<AvaliacaoPartial> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }
}
