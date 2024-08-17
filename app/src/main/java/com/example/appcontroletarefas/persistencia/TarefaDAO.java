package com.example.appcontroletarefas.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.appcontroletarefas.model.Categoria;
import com.example.appcontroletarefas.model.SubTarefa;
import com.example.appcontroletarefas.model.Tarefa;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TarefaDAO implements ICrudDAO {

    private SQLiteDatabase dbEscreve;
    private SQLiteDatabase dbLe;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    public TarefaDAO(Context context) {
        DbHelper db = new DbHelper(context);
        dbEscreve = db.getWritableDatabase();
        dbLe = db.getReadableDatabase();
    }


    @Override
    public boolean salvar(Tarefa tarefa) {
        ContentValues cv = new ContentValues();
        cv.put("titulo", tarefa.getTitulo());
        cv.put("descricao", tarefa.getDescricao());
        cv.put("dataInicio", dateFormat.format(tarefa.getDataInicio()));
        cv.put("dataFim", dateFormat.format(tarefa.getDataFim()));
        cv.put("subTarefa", tarefa.getSubTarefa());
        cv.put("categoria", tarefa.getCategoria());
        cv.put("idUsuario", tarefa.getIdUsuario());
        return false;

    }

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
