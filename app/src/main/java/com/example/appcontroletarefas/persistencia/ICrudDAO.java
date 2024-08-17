package com.example.appcontroletarefas.persistencia;

import java.util.List;

//Estamos criando uma interface generica para ser usado em classes que precisem do CRUD BÁSICO
public interface ICrudDAO<T> {
    boolean salvar(T item);

    boolean alterar(T item);

    boolean deletar(T item);

    List<T> listarTodos();

    T ListarPorId(int id);
}
