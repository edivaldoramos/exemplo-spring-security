package com.github.edivaldoramos.service;

import com.github.edivaldoramos.exceptions.RecursoNaoEncontradoException;
import com.github.edivaldoramos.model.Empresa;
import com.github.edivaldoramos.repository.EmpresaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpresaService implements IEmpresaService {
    private final EmpresaRepository repository;

    @Override
    public void salvar(Empresa empresa) {
        repository.save(empresa);
    }

    @Override
    public Empresa buscarPorId(Long id) throws RecursoNaoEncontradoException {
        Optional<Empresa> empresa = repository.findById(id);
        return empresa.orElseThrow(() ->
                new RecursoNaoEncontradoException("Empresa não encontrado para o id "+id));
    }

    @Override
    public Empresa buscarPorCnpj(String cnpjRaiz) throws RecursoNaoEncontradoException {
        Optional<Empresa> empresa = repository.buscarPorCnpj(cnpjRaiz);
        return empresa.orElseThrow(() ->
                new RecursoNaoEncontradoException("Empresa não encontrado para o cnpj raiz "+cnpjRaiz));
    }

    @Override
    public List<Empresa> buscarPorTodos() {
        return repository.findAll();
    }
}
