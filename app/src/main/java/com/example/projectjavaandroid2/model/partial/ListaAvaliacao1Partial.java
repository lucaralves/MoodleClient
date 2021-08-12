package com.example.projectjavaandroid2.model.partial;

import com.example.projectjavaandroid2.dto.AvaliacaoPartial1DTO;

import java.util.ArrayList;

public class ListaAvaliacao1Partial {

    private ArrayList<Avaliacao1Partial> avaliacoes;

    public ListaAvaliacao1Partial(ArrayList<Avaliacao1Partial> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public ArrayList<Avaliacao1Partial> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(ArrayList<Avaliacao1Partial> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }
}
