package com.example.appcontroletarefas.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "tarefas.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TB_USUARIO = "usuario";

    public static final String TB_SUBTAREFA= "subtarefa";
    public static final String TB_TAREFA = "tarefa";

    public static final String TB_CATEGORIA = "categoria";


    //Criação da Tabela de USUARIO
    private static final String SQL_CREATE_USUARIO =
            "CREATE TABLE IF NOT EXISTS "+ TB_USUARIO + " (" +
                    "    id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "    nome TEXT NOT NULL," +
                    "    login TEXT NOT NULL UNIQUE," +
                    "    senha TEXT NOT NULL" +
                    ");";
    //Criação da Tabela de SUBTAREFA
    private static final String SQL_CREATE_SUBTAREFA =
            "CREATE TABLE IF NOT EXISTS " + TB_SUBTAREFA + " (" +
                    "    id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "    descricao TEXT NOT NULL," +
                    "    titulo TEXT NOT NULL," +
                    "    status INTEGER NOT NULL" +
                    ");";


    //Criação da Tabela de CATEGORIA
    private static final String SQL_CREATE_CATEGORIA =
            "CREATE TABLE IF NOT EXISTS " + TB_CATEGORIA + " (" +
                    "    id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "    prioridade TEXT NOT NULL," +
                    "    cor TEXT NOT NULL" +
                    ");";


    //Criação da Tabela de TAREFA
    private static final String SQL_CREATE_TAREFA =
            "CREATE TABLE IF NOT EXISTS " + TB_TAREFA + " (" +
                    "    id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "    titulo TEXT NOT NULL," +
                    "    descricao TEXT NOT NULL," +
                    "    dataInicio TEXT NOT NULL," +
                    "    dataFim TEXT NOT NULL," +
                    "    subTarefa INTEGER," +
                    "    categoria INTEGER," +
                    "    idUsuario INTEGER NOT NULL," +
                    "    FOREIGN KEY (subTarefa) REFERENCES subTarefa(id)," +
                    "    FOREIGN KEY (categoria) REFERENCES categoria(id)," +
                    "    FOREIGN KEY (idUsuario) REFERENCES usuario(id)" +
                    ");";



    //Atributos destinados ao método onUpgrade
    private static final String SQL_DELETE_USARIO = "DROP TABLE IF EXISTS " + TB_USUARIO;
    private static final String SQL_DELETE_TAREFA = "DROP TABLE IF EXISTS " + TB_TAREFA;
    private static final String SQL_DELETE_CATEGORIA = "DROP TABLE IF EXISTS " + TB_CATEGORIA;
    private static final String SQL_DELETE_SUBTAREFA = "DROP TABLE IF EXISTS " + TB_SUBTAREFA;


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        executeSql(db, SQL_CREATE_USUARIO, "USUARIO");
        executeSql(db, SQL_CREATE_TAREFA, "TAREFA");
        executeSql(db, SQL_CREATE_SUBTAREFA, "SUBTAREFA");
        executeSql(db, SQL_CREATE_CATEGORIA, "CATEGORIA");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Primeiro Dropa as tabelas para depois recriá-las. Neste caso só será chamado se houver necessáriamente uma versão diferente da informada no inicio da classe
        executeSql(db, SQL_DELETE_USARIO, "USUARIO");
        executeSql(db, SQL_DELETE_TAREFA, "TAREFA");
        executeSql(db, SQL_DELETE_SUBTAREFA, "SUBTAREFA");
        executeSql(db, SQL_DELETE_CATEGORIA, "CATEGORIA");
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
