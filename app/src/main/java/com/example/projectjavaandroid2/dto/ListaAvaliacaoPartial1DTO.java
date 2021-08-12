package com.example.projectjavaandroid2.dto;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "avaliacoesuc")
public class ListaAvaliacaoPartial1DTO {

    @ElementList(inline = true, required=false)
    private ArrayList<AvaliacaoPartial1DTO> avaliacoes;

    public ListaAvaliacaoPartial1DTO() {
    }

    public ListaAvaliacaoPartial1DTO(ArrayList<AvaliacaoPartial1DTO> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public ArrayList<AvaliacaoPartial1DTO> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(ArrayList<AvaliacaoPartial1DTO> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }
}
