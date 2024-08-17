package com.example.appcontroletarefas.model;

public class SubTarefa {

    private String descricao;
    private int id;
    private String titulo;
    private boolean status;

    public SubTarefa(String descricao, int id, String titulo, boolean status) {
        this.descricao = descricao;
        this.id = id;
        this.titulo = titulo;
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

