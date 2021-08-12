package com.example.projectjavaandroid2.dto;

import com.example.projectjavaandroid2.model.Avaliacao;
import com.example.projectjavaandroid2.model.TipoAvaliacao;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Root;

import java.time.LocalDate;

@Order(elements = {"tipo", "dataAvaliacao", "observacao", "unidadeCurricular", "refAluno"})
@Root(name = "avaliacao")
public class AvaliacaoDTO {

    @Element(name = "tipo")
    private String tipo;
    @Element(name = "dataAvaliacao")
    private String dataAvaliacao;
    @Element(name = "observacao")
    private String observacao;
    @Element(name = "unidadeCurricular")
    private String unidadeCurricular;
    @Element(name = "refAluno")
    private Long refAluno;

    public AvaliacaoDTO() {
    }

    public AvaliacaoDTO(Avaliacao avaliacao, String uc) {
        this.dataAvaliacao = avaliacao.getDataAvaliacao();
        this.observacao = avaliacao.getObservacao();
        this.refAluno = avaliacao.getRefAluno();
        this.tipo = avaliacao.getTipo();
        this.unidadeCurricular = uc;
    }

    public AvaliacaoDTO(Avaliacao avaliacao) {
        this.dataAvaliacao = avaliacao.getDataAvaliacao();
        this.observacao = avaliacao.getObservacao();
        this.refAluno = avaliacao.getRefAluno();
        this.tipo = avaliacao.getTipo();
        this.unidadeCurricular = avaliacao.getUnidadeCurricular();
    }

    public AvaliacaoDTO(String tipo, String dataAvaliacao,
                        String observacao, String unidadeCurricular, Long refAluno) {
        this.tipo = tipo;
        this.dataAvaliacao = dataAvaliacao;
        this.observacao = observacao;
        this.unidadeCurricular = unidadeCurricular;
        this.refAluno = refAluno;
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
