package com.github.edivaldoramos.service;

import com.github.edivaldoramos.exceptions.RecursoNaoEncontradoException;
import com.github.edivaldoramos.model.Filial;

import java.util.List;

public interface IFilialService {
    void salvar(Filial filial);
    Filial buscarPorId(Long id) throws RecursoNaoEncontradoException;
    Filial buscarPorCnpj(String cnpj) throws RecursoNaoEncontradoException;
    List<Filial> buscarTodos();
}
