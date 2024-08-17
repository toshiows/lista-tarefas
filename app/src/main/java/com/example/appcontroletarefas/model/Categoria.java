package com.example.appcontroletarefas.model;

public class Categoria {

    private int id;
    private String prioridade;

    private String cor;

    public Categoria(int id, String prioridade, String cor) {
        this.id = id;
        this.prioridade = prioridade;
        this.cor = cor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
