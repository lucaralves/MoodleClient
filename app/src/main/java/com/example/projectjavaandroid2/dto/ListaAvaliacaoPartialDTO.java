package com.example.projectjavaandroid2.dto;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "avaliacoesgeral")
public class ListaAvaliacaoPartialDTO {

    @ElementList(inline = true, required=false)
    private ArrayList<AvaliacaoPartialDTO> avaliacoes;

    public ListaAvaliacaoPartialDTO() {
    }

    public ListaAvaliacaoPartialDTO(ArrayList<AvaliacaoPartialDTO> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public ArrayList<AvaliacaoPartialDTO> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(ArrayList<AvaliacaoPartialDTO> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }
}
