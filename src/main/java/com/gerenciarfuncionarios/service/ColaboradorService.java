package com.gerenciarfuncionarios.service;

import com.gerenciarfuncionarios.model.Colaborador;

import java.util.List;

public interface ColaboradorService<T extends Colaborador> {

    T buscar(Long id);

    List<T> listar();
    T criar(T t);
    T atualizar(T t, Long id);
    boolean deletar(Long id);

}
