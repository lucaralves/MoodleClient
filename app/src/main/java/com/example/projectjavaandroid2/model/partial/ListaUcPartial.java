package com.example.projectjavaandroid2.model.partial;

import com.example.projectjavaandroid2.dto.UnidadeCurricularPartialDTO;

import java.util.ArrayList;

public class ListaUcPartial {

    private ArrayList<UnidadeCurricularPartial> ucs;

    public ListaUcPartial(ArrayList<UnidadeCurricularPartial> ucs) {
        this.ucs = ucs;
    }

    public ArrayList<UnidadeCurricularPartial> getUcs() {
        return ucs;
    }

    public void setUcs(ArrayList<UnidadeCurricularPartial> ucs) {
        this.ucs = ucs;
    }
}
