package com.github.edivaldoramos.service;

import com.github.edivaldoramos.exceptions.RecursoNaoEncontradoException;
import com.github.edivaldoramos.model.Empresa;

import java.util.List;

public interface IEmpresaService {
    void salvar(Empresa empresa);
    Empresa buscarPorId(Long id) throws RecursoNaoEncontradoException;
    Empresa buscarPorCnpj(String cnpjRaiz) throws RecursoNaoEncontradoException;
    List<Empresa> buscarPorTodos();
}
