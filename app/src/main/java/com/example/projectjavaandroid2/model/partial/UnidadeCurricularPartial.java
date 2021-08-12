package com.example.projectjavaandroid2.model.partial;

public class UnidadeCurricularPartial {

    private String nome;
    private Integer keyUnidadeCurricular;

    public UnidadeCurricularPartial(String nome, Integer keyUnidadeCurricular) {
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
