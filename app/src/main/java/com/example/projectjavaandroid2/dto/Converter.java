package com.example.projectjavaandroid2.dto;

import com.example.projectjavaandroid2.model.Avaliacao;
import com.example.projectjavaandroid2.model.TipoAvaliacao;
import com.example.projectjavaandroid2.model.partial.*;

import java.util.ArrayList;

public class Converter {

    public static AlunoPartial convertAlunoPartialDTO(AlunoPartialDTO alunoDTO) throws NullPointerException {
        AlunoPartial aluno;
        aluno = new AlunoPartial(alunoDTO);

        return aluno;
    }

    public static Avaliacao convertAvaliacaoDTO(AvaliacaoDTO avaliacaoDTO) throws NullPointerException {
        Avaliacao avaliacao;
        avaliacao = new Avaliacao(avaliacaoDTO);

        return avaliacao;
    }

    public static AvaliacaoDTO convertAvaliacaoEdit(Avaliacao avaliacao) throws NullPointerException {
        AvaliacaoDTO avaliacaoDTO;
        avaliacaoDTO = new AvaliacaoDTO(avaliacao);

        return avaliacaoDTO;
    }

    public static AvaliacaoDTO convertAvaliacaoAdd(Avaliacao avaliacao) throws NullPointerException {
        AvaliacaoDTO avaliacaoDTO;
        avaliacaoDTO = new AvaliacaoDTO(avaliacao, "xxxxxx");

        return avaliacaoDTO;
    }

    public static AvaliacaoPartial convertAvaliacaoPartialDTO(AvaliacaoPartialDTO avaliacaoPartialDTO) {
        AvaliacaoPartial avaliacaoPartial;
        avaliacaoPartial = new AvaliacaoPartial(avaliacaoPartialDTO);

        return avaliacaoPartial;
    }

    public static Avaliacao1Partial convertAvaliacaoPartial1DTO(AvaliacaoPartial1DTO avaliacaoPartial1DTO) {
        Avaliacao1Partial avaliacao1Partial;
        avaliacao1Partial = new Avaliacao1Partial(avaliacaoPartial1DTO);

        return avaliacao1Partial;
    }

    public static UnidadeCurricularPartial convertUnidadeCurricularPartialDTO(UnidadeCurricularPartialDTO ucPartialDTO) throws NullPointerException {
        UnidadeCurricularPartial ucPartial = null;
        ucPartial = new UnidadeCurricularPartial(ucPartialDTO.getNome(), ucPartialDTO.getKeyUnidadeCurricular());
        return ucPartial;
    }

    public static ListaUcPartial convertListaUcPartialDTO(ListaUcPartialDTO listaUcPartialDTO) throws NullPointerException {
        ArrayList<UnidadeCurricularPartial> ucPartials = new ArrayList<UnidadeCurricularPartial>();
        ArrayList<UnidadeCurricularPartialDTO> ucPartialsDTO = listaUcPartialDTO.getUcs();
        if(ucPartialsDTO != null) {
            for (int i = 0; i < ucPartialsDTO.size(); i++) {
                try {
                    UnidadeCurricularPartialDTO ucPartialDTO = ucPartialsDTO.get(i);
                    UnidadeCurricularPartial ucPartial = convertUnidadeCurricularPartialDTO(ucPartialDTO);
                    ucPartials.add(ucPartial);
                } catch (NullPointerException e) {
                    //Nada é acrescentado ao ArrayList.
                }
            }
        }
        ListaUcPartial listaUcPartial = new ListaUcPartial(ucPartials);
        return listaUcPartial;
    }

    public static ListaAvaliacaoPartial convertListaAvaliacaoPartialDTO(ListaAvaliacaoPartialDTO listaAvaliacaoPartialDTO) throws NullPointerException {
        ArrayList<AvaliacaoPartial> avaliacaoPartials = new ArrayList<AvaliacaoPartial>();
        ArrayList<AvaliacaoPartialDTO> avaliacaoPartialsDTO = listaAvaliacaoPartialDTO.getAvaliacoes();
        if(avaliacaoPartialsDTO != null) {
            for (int i = 0; i < avaliacaoPartialsDTO.size(); i++) {
                try {
                    AvaliacaoPartialDTO avaliacaoPartialDTO = avaliacaoPartialsDTO.get(i);
                    AvaliacaoPartial avaliacaoPartial = convertAvaliacaoPartialDTO(avaliacaoPartialDTO);
                    avaliacaoPartials.add(avaliacaoPartial);
                } catch (NullPointerException e) {
                    //Nada é acrescentado ao ArrayList.
                }
            }
        }
        ListaAvaliacaoPartial listaAvaliacaoPartial = new ListaAvaliacaoPartial(avaliacaoPartials);
        return listaAvaliacaoPartial;
    }

    public static ListaAvaliacao1Partial convertListaAvaliacaoPartial1DTO(ListaAvaliacaoPartial1DTO listaAvaliacaoPartial1DTO) throws NullPointerException {
        ArrayList<Avaliacao1Partial> avaliacao1Partials = new ArrayList<Avaliacao1Partial>();
        ArrayList<AvaliacaoPartial1DTO> avaliacao1PartialsDTO = listaAvaliacaoPartial1DTO.getAvaliacoes();
        if(avaliacao1PartialsDTO != null) {
            for (int i = 0; i < avaliacao1PartialsDTO.size(); i++) {
                try {
                    AvaliacaoPartial1DTO avaliacaoPartial1DTO = avaliacao1PartialsDTO.get(i);
                    Avaliacao1Partial avaliacao1Partial = convertAvaliacaoPartial1DTO(avaliacaoPartial1DTO);
                    avaliacao1Partials.add(avaliacao1Partial);
                } catch (NullPointerException e) {
                    //Nada é acrescentado ao ArrayList.
                }
            }
        }
        ListaAvaliacao1Partial listaAvaliacao1Partial = new ListaAvaliacao1Partial(avaliacao1Partials);
        return listaAvaliacao1Partial;
    }
}
