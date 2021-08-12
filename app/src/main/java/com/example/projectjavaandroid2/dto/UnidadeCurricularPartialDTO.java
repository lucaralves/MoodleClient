package com.example.projectjavaandroid2.dto;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Root;

@Order(elements = {"nome", "keyUnidadeCurricular"})
@Root(name = "ucs")
public class UnidadeCurricularPartialDTO {

    @Element(name = "nome")
    private String nome;
    @Element(name = "keyUnidadeCurricular")
    private Integer keyUnidadeCurricular;

    public UnidadeCurricularPartialDTO() {
    }

    public UnidadeCurricularPartialDTO(String nome, Integer keyUnidadeCurricular) {
        this.nome = nome;
        this.keyUnidadeCurricular = keyUnidadeCurricular;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getKeyUnidadeCurricular() {
        return keyUnidadeCurricular;
    }

    public void setKeyUnidadeCurricular(Integer keyUnidadeCurricular) {
        this.keyUnidadeCurricular = keyUnidadeCurricular;
    }
}
