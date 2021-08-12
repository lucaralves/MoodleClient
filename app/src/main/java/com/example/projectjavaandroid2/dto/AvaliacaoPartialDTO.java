package com.example.projectjavaandroid2.dto;

import com.example.projectjavaandroid2.model.TipoAvaliacao;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Root;

@Order(elements = {"tipoAvaliacao", "unidadeCurricular", "keyAvaliacao"})
@Root(name = "avaliacoes")
public class AvaliacaoPartialDTO {

    @Element(name = "tipoAvaliacao")
    TipoAvaliacao tipoAvaliacao;
    @Element(name = "unidadeCurricular")
    String unidadeCurricular;
    @Element(name = "keyAvaliacao")
    Integer keyAvaliacao;

    public AvaliacaoPartialDTO() {
    }

    public AvaliacaoPartialDTO(TipoAvaliacao tipoAvaliacao, String unidadeCurricular) {
        this.tipoAvaliacao = tipoAvaliacao;
        this.unidadeCurricular = unidadeCurricular;
    }

    public TipoAvaliacao getTipoAvaliacao() {
        return tipoAvaliacao;
    }

    public void setTipoAvaliacao(TipoAvaliacao tipoAvaliacao) {
        this.tipoAvaliacao = tipoAvaliacao;
    }

    public String getUnidadeCurricular() {
        return unidadeCurricular;
    }

    public void setUnidadeCurricular(String unidadeCurricular) {
        this.unidadeCurricular = unidadeCurricular;
    }

    public Integer getKeyAvaliacao() {
        return keyAvaliacao;
    }
}
