package com.example.projectjavaandroid2.dto;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "erro")
public class ErroDTO {
    @Element(name = "mensagem")
    private String mensagemErro;

    public ErroDTO(Exception e) {
        mensagemErro = e.getMessage();
       // e.printStackTrace();
    }


    public ErroDTO() {
    }

    public String getMensagemErro() {
        return mensagemErro;
    }

    public void setMensagemErro(String mensagemErro) {
        this.mensagemErro = mensagemErro;
    }
}
