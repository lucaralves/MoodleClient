package com.example.projectjavaandroid2.model.partial;

import com.example.projectjavaandroid2.dto.AvaliacaoPartial1DTO;
import com.example.projectjavaandroid2.model.TipoAvaliacao;

import java.time.LocalDate;

public class Avaliacao1Partial {

    TipoAvaliacao tipoAvaliacao;
    String data;
    Integer keyAvaliacao;

    public Avaliacao1Partial(TipoAvaliacao tipoAvaliacao, String data, Integer keyAvaliacao) {
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

    public Avaliacao1Partial(AvaliacaoPartial1DTO avaliacaoPartial1DTO) {
        this.tipoAvaliacao = avaliacaoPartial1DTO.getTipoAvaliacao();
        this.data = avaliacaoPartial1DTO.getData();
        this.keyAvaliacao = avaliacaoPartial1DTO.getKeyAvaliacao();
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
