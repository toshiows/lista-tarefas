package com.example.appcontroletarefas.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Tarefa implements Serializable {

    private int id;
    private String titulo;

    private String descricao;
    private Date dataInicio;
    private Date dataFim;
    private List<SubTarefa> subTarefa;
    private List<Categoria> categoria;
    private int idUsuario;

    public Tarefa(int id, String titulo, String descricao, Date dataInicio, Date dataFim, List<SubTarefa> subTarefa, List<Categoria> categoria, int idUsuario) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.subTarefa = subTarefa;
        this.categoria = categoria;
        this.idUsuario = idUsuario;
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

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    public List<SubTarefa> getSubTarefa() {
        return subTarefa;
    }

    public void setSubTarefa(List<SubTarefa> subTarefa) {
        this.subTarefa = subTarefa;
    }

    public List<Categoria> getCategoria() {
        return categoria;
    }

    public void setCategoria(List<Categoria> categoria) {
        this.categoria = categoria;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}
