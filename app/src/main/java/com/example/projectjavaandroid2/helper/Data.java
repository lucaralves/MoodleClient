package com.example.projectjavaandroid2.helper;

public class Data {

    int dia, mes, ano;

    public Data(int dia, int mes, int ano) {
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getData() {
        String data;

        if (Integer.toString(mes).length() == 2) {
            data = Integer.toString(ano) + '-' + mes;
        }
        else {
            data = Integer.toString(ano) + '-' + 0 + mes;
        }

        if (Integer.toString(dia).length() == 2) {
            data = data + '-' + dia;
        }
        else {
            data = data + '-' + 0 + dia;
        }

        return data;
    }
}
