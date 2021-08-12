package com.example.projectjavaandroid2.model.partial;

import com.example.projectjavaandroid2.dto.AlunoPartialDTO;

public class AlunoPartial {

    private Long numAluno;
    private long senha;

    public AlunoPartial(Long numAluno, long senha) {
        this.numAluno = numAluno;
        this.senha = senha;
    }

    public AlunoPartial(AlunoPartialDTO alunoPartialDTO) {
        this.numAluno = alunoPartialDTO.getNumAluno();
        this.senha = alunoPartialDTO.getSenha();
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
}
