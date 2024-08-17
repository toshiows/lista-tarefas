package com.example.controlefinanceiro.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "financeiro.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TB_DESPESAS = "despesas";
    public static final String TB_RECEITAS = "receitas";
   // public static final String TB_CATEGORIAS = "categorias";
    //Criação da Tabela de Desepesas
    private static final String SQL_CREATE_DESPESAS =
            "CREATE TABLE IF NOT EXISTS " + TB_DESPESAS + " (" +
                    "id_despesa INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "descricao_despesa TEXT, " +
                    "valor_despesa DOUBLE, " +
                    "data_despesa TEXT, " +
                    "id_categoria INTEGER);";
    //Criação da Tabela de Receitas
    private static final String SQL_CREATE_RECEITAS =
            "CREATE TABLE IF NOT EXISTS " + TB_RECEITAS + " (" +
                    "id_receita INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "descricao_receita TEXT, " +
                    "valor_receita DOUBLE, " +
                    "data_receita TEXT, " +
                    "id_categoria INTEGER);";

    //Atributos destinados ao método onUpgrade
    private static final String SQL_DELETE_DESPESAS = "DROP TABLE IF EXISTS " + TB_DESPESAS;
    private static final String SQL_DELETE_RECEITAS = "DROP TABLE IF EXISTS " + TB_RECEITAS;


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        executeSql(db, SQL_CREATE_DESPESAS, "DESPESAS");
        executeSql(db, SQL_CREATE_RECEITAS, "RECEITAS");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Primeiro Dropa as tabelas para depois recriá-las. Neste caso só será chamado se houver necessáriamente uma versão diferente da informada no inicio da classe
        executeSql(db, SQL_DELETE_DESPESAS, "DESPESAS");
        executeSql(db, SQL_DELETE_RECEITAS, "RECEITAS");
        onCreate(db);
    }
    //Criada uma função executeSql para reduzir a repetição de código nos métodos, objetivo de melhorar a legibilidade
    private void executeSql(SQLiteDatabase db, String sql, String tableName) {
        try {
            db.execSQL(sql);
            Log.i("INFO DB", "SUCESSO AO CRIAR A TABELA " + tableName);
        } catch (Exception e) {
            Log.e("INFO DB", "ERRO AO CRIAR A TABELA " + tableName + " " + e.getMessage());
        }
    }
}
