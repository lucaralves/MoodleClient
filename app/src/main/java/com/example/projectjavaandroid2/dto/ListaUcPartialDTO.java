package com.example.projectjavaandroid2.dto;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.ArrayList;

@Root(name = "unidadescurriculares")
public class ListaUcPartialDTO {

    @ElementList(inline = true, required=false)
    private ArrayList<UnidadeCurricularPartialDTO> ucs;

    public ListaUcPartialDTO() {
    }

    public ListaUcPartialDTO(ArrayList<UnidadeCurricularPartialDTO> ucs) {
        this.ucs = ucs;
    }

    public ArrayList<UnidadeCurricularPartialDTO> getUcs() {
        return ucs;
    }

    public void setUcs(ArrayList<UnidadeCurricularPartialDTO> ucs) {
        this.ucs = ucs;
    }
}
