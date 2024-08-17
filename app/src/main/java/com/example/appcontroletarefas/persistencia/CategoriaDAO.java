package com.example.appcontroletarefas.persistencia;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.appcontroletarefas.model.Categoria;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO implements ICrudDAO<Categoria> {

    private SQLiteDatabase dbEscreve;
    private SQLiteDatabase dbLe;


    @Override
    public boolean salvar(Categoria categoria) {
        ContentValues cv = new ContentValues();
        cv.put("prioridade", categoria.getPrioridade());
        cv.put("cor", categoria.getCor());

        try{
            long id =  dbEscreve.insert(DbHelper.TB_CATEGORIA, null, cv);
            if (id != -1) {
                Log.i("InfoDB", "Sucesso ao salvar o registro na tabela Despesa");
                return true;
            } else {
                Log.i("InfoDB", "Erro ao salvar o registro na tabela Despesa");
                return false;
            }
        } catch (Exception e) {
            Log.e("InfoDB", "Erro ao salvar o registro: " + e.getMessage());
            return false;
        }
        }



    @Override
    public boolean alterar(Categoria categoria) {
        ContentValues cv = new ContentValues();
        cv.put("prioridade", categoria.getPrioridade());
        cv.put("cor", categoria.getCor());

        try {
            String[] args = {String.valueOf(categoria.getId())};
            int rowsAffected = dbEscreve.update(DbHelper.TB_CATEGORIA, cv, "id=?", args);
            if (rowsAffected > 0) {
                Log.i("InfoDB", "Sucesso ao atualizar o registro na tabela Despesa");
                return true;
            } else {
                Log.i("InfoDB", "Erro ao atualizar o registro na tabela Despesa");
                return false;
            }
        } catch (Exception e) {
            Log.e("InfoDB", "Erro ao atualizar o registro: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(Categoria categoria) {
        try {
            String[] args = {String.valueOf(categoria.getId())};
            int rowsAffected = dbEscreve.delete(DbHelper.TB_CATEGORIA, "id=?", args);
            if (rowsAffected > 0) {
                Log.i("InfoDB", "Sucesso ao excluir o registro na tabela Despesa");
                return true;
            } else {
                Log.i("InfoDB", "Erro ao excluir o registro na tabela Despesa");
                return false;
            }
        } catch (Exception e) {
            Log.e("InfoDB", "Erro ao excluir o registro: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Categoria> listarTodos() {
        List<Categoria> lstCategoria = new ArrayList<>();
        String sql = "SELECT * FROM " + DbHelper.TB_CATEGORIA + ";";
        try (Cursor cursor = dbLe.rawQuery(sql, null)) {
            while (cursor.moveToNext()) {
                int idColumnIndex = cursor.getColumnIndex("id");
                int corColumnIndex = cursor.getColumnIndex("cor");
                int prioridadeColumnIndex = cursor.getColumnIndex("prioridade");

                if (idColumnIndex != -1 && corColumnIndex != -1 && prioridadeColumnIndex != -1) {
                    int id = cursor.getInt(idColumnIndex);
                    String cor = cursor.getString(corColumnIndex);
                    String prioridade = cursor.getString(prioridadeColumnIndex);
                    Categoria categoria = new Categoria(id, cor, prioridade);
                    lstCategoria.add(categoria);
                    Log.i("InfoDB", "Sucesso ao pesquisar o registro na tabela Despesa");
                } else {
                    // Trate o caso onde uma ou mais colunas não foram encontradas
                    Log.i("InfoDB","Uma ou mais colunas não foram encontradas no cursor.");
                }
                // Conversão da data

            }
        } catch (Exception e) {
            Log.e("InfoDB", "Erro ao pesquisar o registro: " + e.getMessage());
        }
        return lstCategoria;
    }

    @Override
    public Categoria ListarPorId(int id) {
        String sql = "SELECT * FROM " + DbHelper.TB_CATEGORIA + " WHERE id = ?;";
        try (Cursor cursor = dbLe.rawQuery(sql, new String[]{String.valueOf(id)})) {
            if (cursor.moveToNext()) {


                int idColumnIndex = cursor.getColumnIndex("id");
                int corColumnIndex = cursor.getColumnIndex("cor");

                int  prioridadeColumnIndex = cursor.getColumnIndex("prioridade");

                String cor = cursor.getString(corColumnIndex);
                String prioridade = cursor.getString(prioridadeColumnIndex);
                int idCategoria = cursor.getInt(idColumnIndex);
                return new Categoria(idCategoria, prioridade, cor);
            }
        } catch (Exception e) {
            Log.e("InfoDB", "Erro ao pesquisar o registro por ID: " + e.getMessage());
        }
        return null;
    }
}
