package com.example.projectjavaandroid2.model;

import com.example.projectjavaandroid2.dto.AvaliacaoDTO;

import java.time.LocalDate;

public class Avaliacao {

    private String tipo;
    private String dataAvaliacao;
    private String observacao;
    private String unidadeCurricular;
    private Long refAluno;

    public Avaliacao(String tipo, String dataAvaliacao,
                     String observacao, String unidadeCurricular, Long refAluno) {
        this.tipo = tipo;
        this.dataAvaliacao = dataAvaliacao;
        this.observacao = observacao;
        this.unidadeCurricular = unidadeCurricular;
        this.refAluno = refAluno;
    }

    public Avaliacao(String tipo, String dataAvaliacao,
                     String observacao, Long refAluno) {
        this.tipo = tipo;
        this.dataAvaliacao = dataAvaliacao;
        this.observacao = observacao;
        this.unidadeCurricular = unidadeCurricular;
        this.refAluno = refAluno;
    }

    public Avaliacao(AvaliacaoDTO avaliacaoDTO) {
        this.tipo = avaliacaoDTO.getTipo();
        this.dataAvaliacao = avaliacaoDTO.getDataAvaliacao();
        this.unidadeCurricular = avaliacaoDTO.getUnidadeCurricular();
        this.observacao = avaliacaoDTO.getObservacao();
        this.refAluno = avaliacaoDTO.getRefAluno();
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDataAvaliacao() {
        return dataAvaliacao;
    }

    public void setDataAvaliacao(String dataAvaliacao) {
        this.dataAvaliacao = dataAvaliacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getUnidadeCurricular() {
        return unidadeCurricular;
    }

    public void setUnidadeCurricular(String unidadeCurricular) {
        this.unidadeCurricular = unidadeCurricular;
    }

    public Long getRefAluno() {
        return refAluno;
    }

    public void setRefAluno(Long refAluno) {
        this.refAluno = refAluno;
    }
}
