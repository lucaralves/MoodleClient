package com.example.projectjavaandroid2.model.partial;

import com.example.projectjavaandroid2.dto.AvaliacaoPartialDTO;
import com.example.projectjavaandroid2.model.TipoAvaliacao;

public class AvaliacaoPartial {

    TipoAvaliacao tipoAvaliacao;
    String unidadeCurricular;
    Integer keyAvaliacao;

    public AvaliacaoPartial(TipoAvaliacao tipoAvaliacao, String unidadeCurricular) {
        this.tipoAvaliacao = tipoAvaliacao;
        this.unidadeCurricular = unidadeCurricular;
    }

    public AvaliacaoPartial(AvaliacaoPartialDTO avaliacaoPartialDTO) {
        this.tipoAvaliacao = avaliacaoPartialDTO.getTipoAvaliacao();
        this.unidadeCurricular = avaliacaoPartialDTO.getUnidadeCurricular();
        this.keyAvaliacao = avaliacaoPartialDTO.getKeyAvaliacao();
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

    public void setKeyAvaliacao(Integer keyAvaliacao) {
        this.keyAvaliacao = keyAvaliacao;
    }
}
