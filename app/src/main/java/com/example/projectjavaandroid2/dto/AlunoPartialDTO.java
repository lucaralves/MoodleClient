package com.example.projectjavaandroid2.dto;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Root;

@Order(elements = {"numAluno", "senha", "numeroAluno"})
@Root(name = "alunopartial")
public class AlunoPartialDTO {

    @Element(name = "numAluno")
    private Long numAluno;
    @Element(name = "senha")
    private long senha;
    @Element(name = "numeroAluno")
    private Long numeroluno;

    public AlunoPartialDTO() {
    }

    public AlunoPartialDTO(Long numAluno, long senha) {
        this.numAluno = numAluno;
        this.senha = senha;
    }

    public Long getNumAluno() {
        return numAluno;
    }

    public void setNumAluno(Long numAluno) {
        this.numAluno = numAluno;
    }

    public long getSenha() {
        return senha;
    }

    public void setSenha(long senha) {
        this.senha = senha;
    }

    public Long getNumeroluno() {
        return numeroluno;
    }

    public void setNumeroluno(Long numeroluno) {
        this.numeroluno = numeroluno;
    }
}
