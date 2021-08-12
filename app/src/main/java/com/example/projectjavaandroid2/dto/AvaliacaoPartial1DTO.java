package com.example.projectjavaandroid2.dto;

import com.example.projectjavaandroid2.model.TipoAvaliacao;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Root;

import java.time.LocalDate;

@Order(elements = {"tipoAvaliacao", "data"})
@Root(name = "avaliacoes")
public class AvaliacaoPartial1DTO {

    @Element(name = "tipoAvaliacao")
    TipoAvaliacao tipoAvaliacao;
    @Element(name = "data")
    String data;
    @Element(name = "keyAvaliacao")
    Integer keyAvaliacao;

    public AvaliacaoPartial1DTO() {
    }

    public AvaliacaoPartial1DTO(TipoAvaliacao tipoAvaliacao, String data, Integer keyAvaliacao) {
        this.tipoAvaliacao = tipoAvaliacao;
        this.data = data;
        this.keyAvaliacao = keyAvaliacao;
    }

    public Integer getKeyAvaliacao() {
        return keyAvaliacao;
    }

    public void setKeyAvaliacao(Integer keyAvaliacao) {
        this.keyAvaliacao = keyAvaliacao;
    }

    public TipoAvaliacao getTipoAvaliacao() {
        return tipoAvaliacao;
    }

    public void setTipoAvaliacao(TipoAvaliacao tipoAvaliacao) {
        this.tipoAvaliacao = tipoAvaliacao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
