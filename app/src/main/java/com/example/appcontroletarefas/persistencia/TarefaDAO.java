package com.example.appcontroletarefas.persistencia;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.example.appcontroletarefas.model.Categoria;
import com.example.appcontroletarefas.model.SubTarefa;
import com.example.appcontroletarefas.model.Tarefa;

import java.util.Date;
import java.util.List;

public class TarefaDAO implements ICrudDAO {

    private SQLiteDatabase dbEscreve;
    private SQLiteDatabase dbLe;


    @Override
    public boolean salvar(Tarefa tarefa) {
        ContentValues cv = new ContentValues();
        cv.put("titulo", Tarefa.getTitulo());
        cv.put("descricao", Tarefa.getDescricao());
        cv.put("dataInicio", Tarefa.getDataInicio);
        cv.put("dataFim", Tarefa.getDataFim);
        cv.put("subtarefa", );

        return false;
    }


    private int id;
    private String titulo;

    private String descricao;
    private Date dataInicio;
    private Date dataFim;
    private SubTarefa subTarefa;
    private Categoria categoria;
    private int idUsuario;

    @Override
    public boolean alterar(Object item) {
        return false;
    }

    @Override
    public boolean deletar(Object item) {
        return false;
    }

    @Override
    public List listarTodos() {
        return null;
    }

    @Override
    public Object ListarPorId(int id) {
        return null;
    }
}
