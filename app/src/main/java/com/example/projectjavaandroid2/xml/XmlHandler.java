package com.example.projectjavaandroid2.xml;

import com.example.projectjavaandroid2.dto.*;
import com.example.projectjavaandroid2.model.Avaliacao;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.StringWriter;

public class XmlHandler {

    public static AvaliacaoDTO deSerializeXML2AvaliacaoDTO(String xmlData) {
        AvaliacaoDTO data = null;
        if(xmlData != null){
            Serializer serializer = new Persister();
            try {
                data = serializer.read(AvaliacaoDTO.class, xmlData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public static String serializeAvaliacaoDTO2XML(AvaliacaoDTO avaliacaoDTO) {
        StringWriter writer = new StringWriter();
        if (avaliacaoDTO != null) {
            Serializer serializer = new Persister();
            try {
                serializer.write(avaliacaoDTO, writer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return writer.toString();
    }

    public static AlunoPartialDTO deSerializeXML2PessoaDTO(String xmlData) {
        AlunoPartialDTO data = null;
        if(xmlData != null){
            Serializer serializer = new Persister();
            try {
                data = serializer.read(AlunoPartialDTO.class, xmlData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public static String serializeErroDTO2XML(ErroDTO erroDTO) {
        StringWriter writer = new StringWriter();
        if (erroDTO != null) {
            Serializer serializer = new Persister();
            try {
                serializer.write(erroDTO, writer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return writer.toString();
    }

    //Transforma um erroDTO xml num erroDTO objeto.
    public static ErroDTO deSerializeXML2ErroDTO(String xmlData) {
        ErroDTO data = null;
        if(xmlData != null){
            Serializer serializer = new Persister();
            try {
                data = serializer.read(ErroDTO.class, xmlData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public static ListaUcPartialDTO deSerializeXML2ListaPessoaPartialDTO(String xmlData) {
        ListaUcPartialDTO data = null;
        if(xmlData != null){
            Serializer serializer = new Persister();
            try {
                data = serializer.read(ListaUcPartialDTO.class, xmlData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public static ListaUcPartialDTO deSerializeXML2ListaUcPartialDTO(String xmlData) {
        ListaUcPartialDTO data = null;
        if (xmlData != null) {
            Serializer serializer = new Persister();
            try {
                data = serializer.read(ListaUcPartialDTO.class, xmlData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public static ListaAvaliacaoPartialDTO deSerializeXML2ListaAvaliacaoPartialDTO(String xmlData) {

        ListaAvaliacaoPartialDTO data = null;
        if (xmlData != null) {
            Serializer serializer = new Persister();
            try {
                data = serializer.read(ListaAvaliacaoPartialDTO.class, xmlData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return data;
    }

    public static ListaAvaliacaoPartial1DTO deSerializeXML2ListaAvaliacaoPartial1DTO(String xmlData) {

        ListaAvaliacaoPartial1DTO data = null;
        if (xmlData != null) {
            Serializer serializer = new Persister();
            try {
                data = serializer.read(ListaAvaliacaoPartial1DTO.class, xmlData);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return data;
    }
}
