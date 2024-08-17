package com.example.controlefinanceiro.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.controlefinanceiro.model.Receita;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ReceitaDAO implements ICrudDAO<Receita> {

    private SQLiteDatabase dbEscreve;
    private SQLiteDatabase dbLe;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());

    public ReceitaDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        dbEscreve = dbHelper.getWritableDatabase();
        dbLe = dbHelper.getReadableDatabase();
    }

    @Override
    public boolean salvar(Receita receita) {
        ContentValues cv = new ContentValues();
        cv.put("descricao_receita", receita.getDescricao());
        cv.put("valor_receita", receita.getValor());
        cv.put("data_receita", dateFormat.format(receita.getData()));

        try {
            long id = dbEscreve.insert(DbHelper.TB_RECEITAS, null, cv);
            if (id != -1) {
                Log.i("InfoDB", "Sucesso ao salvar o registro na tabela Receita");
                return true;
            } else {
                Log.i("InfoDB", "Erro ao salvar o registro na tabela Receita");
                return false;
            }
        } catch (Exception e) {
            Log.e("InfoDB", "Erro ao salvar o registro: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean alterar(Receita receita) {
        ContentValues cv = new ContentValues();
        cv.put("descricao_receita", receita.getDescricao());
        cv.put("valor_receita", receita.getValor());
        cv.put("data_receita", dateFormat.format(receita.getData()));

        try {
            String[] args = {String.valueOf(receita.getId())};
            int rowsAffected = dbEscreve.update(DbHelper.TB_RECEITAS, cv, "id_receita=?", args);
            if (rowsAffected > 0) {
                Log.i("InfoDB", "Sucesso ao atualizar o registro na tabela Receita");
                return true;
            } else {
                Log.i("InfoDB", "Erro ao atualizar o registro na tabela Receita");
                return false;
            }
        } catch (Exception e) {
            Log.e("InfoDB", "Erro ao atualizar o registro: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deletar(Receita receita) {
        try {
            String[] args = {String.valueOf(receita.getId())};
            int rowsAffected = dbEscreve.delete(DbHelper.TB_RECEITAS, "id_receita=?", args);
            if (rowsAffected > 0) {
                Log.i("InfoDB", "Sucesso ao excluir o registro na tabela Receita");
                return true;
            } else {
                Log.i("InfoDB", "Erro ao excluir o registro na tabela Receita");
                return false;
            }
        } catch (Exception e) {
            Log.e("InfoDB", "Erro ao excluir o registro: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Receita> listarTodos() {
        List<Receita> lstReceitas = new ArrayList<>();
        String sql = "SELECT * FROM " + DbHelper.TB_RECEITAS + ";";
        try (Cursor cursor = dbLe.rawQuery(sql, null)) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("id_receita"));
                String descricao = cursor.getString(cursor.getColumnIndex("descricao_receita"));
                double valor = cursor.getDouble(cursor.getColumnIndex("valor_receita"));
                Date data = dateFormat.parse(cursor.getString(cursor.getColumnIndex("data_receita")));
                Receita receita = new Receita(id, descricao, valor, data);
                lstReceitas.add(receita);
                Log.i("InfoDB", "Sucesso ao pesquisar o registro na tabela Receita");
            }
        } catch (Exception e) {
            Log.e("InfoDB", "Erro ao pesquisar o registro: " + e.getMessage());
        }
        return lstReceitas;
    }

    @Override
    public Receita ListarPorId(int id) {
        String sql = "SELECT * FROM " + DbHelper.TB_RECEITAS + " WHERE id_receita = ?;";
        try (Cursor cursor = dbLe.rawQuery(sql, new String[]{String.valueOf(id)})) {
            if (cursor.moveToNext()) {
                int idReceita = cursor.getInt(cursor.getColumnIndex("id_receita"));
                String descricao = cursor.getString(cursor.getColumnIndex("descricao_receita"));
                double valor = cursor.getDouble(cursor.getColumnIndex("valor_receita"));
                Date data = dateFormat.parse(cursor.getString(cursor.getColumnIndex("data_receita")));

                return new Receita(idReceita, descricao, valor, data);
            }
        } catch (Exception e) {
            Log.e("InfoDB", "Erro ao pesquisar o registro por ID: " + e.getMessage());
        }
        return null;
    }


}