package com.example.controlefinanceiro.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.controlefinanceiro.model.Despesa;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DespesaDAO implements ICrudDAO<Despesa>{

    private SQLiteDatabase dbEscreve;
    private SQLiteDatabase dbLe;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
    public DespesaDAO(Context context) {
        DbHelper db = new DbHelper(context);
        dbEscreve = db.getWritableDatabase();
        dbLe = db.getReadableDatabase();
    }
    @Override
    public boolean salvar(Despesa despesa) {
        ContentValues cv = new ContentValues();
        cv.put("descricao_despesa", despesa.getDescricao());
        cv.put("valor_despesa", despesa.getValor());
        cv.put("data_despesa", dateFormat.format(despesa.getData()));

        try {
            long id = dbEscreve.insert(DbHelper.TB_DESPESAS, null, cv);
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
    public boolean alterar(Despesa item) {
        ContentValues cv = new ContentValues();
        cv.put("descricao_despesa", item.getDescricao());
        cv.put("valor_despesa", item.getValor());
        cv.put("data_despesa", dateFormat.format(item.getData()));

        try {
            String[] args = {String.valueOf(item.getId())};
            int rowsAffected = dbEscreve.update(DbHelper.TB_DESPESAS, cv, "id_despesa=?", args);
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
    public boolean deletar(Despesa item) {
        try {
            String[] args = {String.valueOf(item.getId())};
            int rowsAffected = dbEscreve.delete(DbHelper.TB_DESPESAS, "id_despesa=?", args);
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
    public List<Despesa> listarTodos() {
        List<Despesa> lstDespesas = new ArrayList<>();
        String sql = "SELECT * FROM " + DbHelper.TB_DESPESAS + ";";
        try (Cursor cursor = dbLe.rawQuery(sql, null)) {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(cursor.getColumnIndex("id_despesa"));
                String descricao = cursor.getString(cursor.getColumnIndex("descricao_despesa"));
                double valor = cursor.getDouble(cursor.getColumnIndex("valor_despesa"));
                // Conversão da data
                Date data = dateFormat.parse(cursor.getString(cursor.getColumnIndex("data_despesa")));
                Despesa despesa = new Despesa(id, descricao, valor, data);
                lstDespesas.add(despesa);
                Log.i("InfoDB", "Sucesso ao pesquisar o registro na tabela Despesa");
            }
        } catch (Exception e) {
            Log.e("InfoDB", "Erro ao pesquisar o registro: " + e.getMessage());
        }
        return lstDespesas;
    }

    @Override
    public Despesa ListarPorId(int id) {
        String sql = "SELECT * FROM " + DbHelper.TB_DESPESAS + " WHERE id_despesa = ?;";
        try (Cursor cursor = dbLe.rawQuery(sql, new String[]{String.valueOf(id)})) {
            if (cursor.moveToNext()) {
                int idDespesa = cursor.getInt(cursor.getColumnIndex("id_despesa"));
                String descricao = cursor.getString(cursor.getColumnIndex("descricao_despesa"));
                double valor = cursor.getDouble(cursor.getColumnIndex("valor_despesa"));
                Date data = dateFormat.parse(cursor.getString(cursor.getColumnIndex("data_despesa")));
              //  int idCategoria = cursor.getInt(cursor.getColumnIndex("id_categoria"));
                return new Despesa(idDespesa, descricao, valor, data);
            }
        } catch (Exception e) {
            Log.e("InfoDB", "Erro ao pesquisar o registro por ID: " + e.getMessage());
        }
        return null;
    }
}
